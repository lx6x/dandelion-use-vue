package com.dandelion.use.server.service.user.service.impl;

import com.dandelion.use.server.core.constant.RedisConstant;
import com.dandelion.use.server.core.properties.TokenCustomProperties;
import com.dandelion.use.server.core.security.util.JwtTokenUtil;
import com.dandelion.use.server.core.utils.RedisUtil;
import com.dandelion.use.server.service.user.service.LoginService;
import com.dandelion.use.server.service.user.service.impl.security.UserDetailImpl;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public String login(String username, String password) {
        UserDetailImpl userDetails = (UserDetailImpl) userDetailsService.loadUserByUsername(username);
        String userDetailsPassword = userDetails.getPassword();
        boolean matches = passwordEncoder.matches(password, userDetailsPassword);
        if (!matches) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenCustomProperties.getPrefix().concat(" ").concat(jwtTokenUtil.generateToken(userDetails));
        Integer id = userDetails.getSysUser().getId();
        // jwt 无状态，使用 redis 做主动下线
        redisUtil.set(RedisConstant.TOKEN + id, token, tokenCustomProperties.getExpireTime());
        return token;
    }

    @Override
    public boolean logout() {
        // 获取SecurityContextHolder里的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        Integer id = userDetails.getSysUser().getId();
        redisUtil.del(RedisConstant.TOKEN + id);
        return true;
    }
}
