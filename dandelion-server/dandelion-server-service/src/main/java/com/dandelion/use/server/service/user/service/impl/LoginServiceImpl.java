package com.dandelion.use.server.service.user.service.impl;

import com.dandelion.use.server.core.constant.RedisConstant;
import com.dandelion.use.server.core.exception.GlobalException;
import com.dandelion.use.server.core.security.properties.TokenCustomProperties;
import com.dandelion.use.server.core.security.util.JwtTokenUtil;
import com.dandelion.use.server.core.utils.RedisUtil;
import com.dandelion.use.server.service.user.repository.dao.SysUserDao;
import com.dandelion.use.server.service.user.repository.entity.SysUser;
import com.dandelion.use.server.service.user.service.LoginService;
import com.dandelion.use.server.service.user.service.impl.security.UserDetailImpl;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 登录业务实现
 *
 * @author lx6x
 * @date 2023/10/27
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private TokenCustomProperties tokenCustomProperties;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public String login(String username, String password) {
        UserDetailImpl userDetails = (UserDetailImpl) userDetailsService.loadUserByUsername(username);
        String userDetailsPassword = userDetails.getPassword();
        boolean matches = passwordEncoder.matches(password, userDetailsPassword);
        if (!matches) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, userDetailsPassword,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        // jwt 无状态，使用 redis 做主动下线
        redisUtil.set(RedisConstant.TOKEN.concat(username), token, tokenCustomProperties.getExpireTime());
        return tokenCustomProperties.getPrefix().concat(" ").concat(token);
    }

    @Override
    public boolean logout() {
        // 获取SecurityContextHolder里的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        String username = userDetails.getUsername();
        redisUtil.del(RedisConstant.TOKEN.concat(username));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(SysUser sysUser) {
        String password = sysUser.getPassword();
        String encode = passwordEncoder.encode(password);
        sysUser.setPassword(encode);
        boolean addUser;
        try {
            addUser = sysUserDao.addUser(sysUser);
        } catch (GlobalException e) {
            throw new GlobalException(e.getMessage());
        }
        return addUser;
    }
}
