# SpringSecurity通过角色鉴权

## 概述
主要通过 [GrantedAuthority]() 这个接口实现，接口只有一种方法 `String getAuthority()` 代表已授予的权限,在做出授权决策时由实例读取。<br />当前只是用 `角色` 处理权限，那默认情况下，基于角色的授权规则作为前缀包含在内。这意味着，如果存在要求安全上下文具有“USER”角色的授权规则，则 Spring Security 将默认查找返回“ROLE_USER”的授权规则。

## 角色验证部分实现

### 创建角色表
```sql
CREATE TABLE `sys_role`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色信息';

INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (1, 'admin');
INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (2, 'user1');
INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (3, 'user2');

CREATE TABLE `sys_user_role`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户id',
    `role_id` bigint NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色关联信息';

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`)
VALUES (1, 1, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`)
VALUES (2, 2, 2);
```

### 修改 UserDetailService 查询用户信息
```xml
<resultMap id="queryUserRoleMap" type="com.dandelion.use.server.service.user.repository.entity.SysUser" autoMapping="true">
  <id column="id" property="id" />
  <result column="user_name" property="userName" />
  <result column="password" property="password" />
  <result column="nick_name" property="nickName" />
  <result column="sex" property="sex" />
  <result column="create_time" property="createTime" />
  <result column="update_time" property="updateTime" />
  <collection property="roles" ofType="com.dandelion.use.server.service.user.repository.entity.SysRole" autoMapping="true" columnPrefix="r_">
    <id column="id" property="id"/>
    <result column="roleName" property="roleName"/>
  </collection>
</resultMap>

<select id="getUserRoleInfo" resultMap="queryUserRoleMap">
  SELECT su.*,
  sr.id        AS r_id,
  sr.role_name as r_roleName
  FROM sys_user AS su
  LEFT JOIN sys_user_role AS sur ON su.id = sur.user_id
  LEFT JOIN sys_role AS sr ON sur.role_id = sr.id
  WHERE su.user_name = #{userName}
</select>
```

### 实现 GrantedAuthority 
```java
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "GrantedAuthorityImpl{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
```

### 修改 UserDetailImpl
特别注意下这个，主要是修改 getAuthorities() 方法中所返回的内容，这里使用 "ROLE_" 拼接是由于 Security 使用角色鉴权时默认会判断是否有该前缀。
```java
public class UserDetailImpl implements UserDetails {

    /**
     * 将应用程序用户的权限返回成一个GrantedAuthority实例集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SysRole> roles = this.sysUser.getRoles();
        return roles.stream().map(map -> new GrantedAuthorityImpl("ROLE_".concat(map.getRoleName()))).collect(Collectors.toList());
    }

}

```
```java
public abstract class SecurityExpressionRoot implements SecurityExpressionOperations {
    // 省略...
    private String defaultRolePrefix = "ROLE_";
    // 省略...
}

```

## 验证
在控制器中使用 `@PreAuthorize("hasRole('role_name')")`，`hasRole` 会自动拼接前缀内容，也可以使用 `hasAuthority`

