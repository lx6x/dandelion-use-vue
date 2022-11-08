# dandelion-use

## 模块结构

```
dandelion-use
├── dandelion-client                         页面
|   ├── dandelion-domino-ui                  domino ui 应用
|   |   ├── dandelion-domino-ui-client       实际 ui 页面
|   |   ├── dandelion-domino-ui-server       与 dandelion-server 交互使用
|   └── └── dandelion-domino-ui-shared       client 与 server 交互使用，如：实体类映射
├── dandelion-server                         服务 (仿照 ruoyi )
|   |   ├── dandelion-server-admin           实际对外抛出服务
|   |   ├── dandelion-server-common          公共模块
| | |   ├── dandelion-server-framework       核心模块
└── └── └── dandelion-server-system          系统模块
```

## 技术选型

| 框架             | 版本                | 说明               | 
|----------------|-------------------|------------------|
| Domino-UI      | 1.0-rc.4-SNAPSHOT | 前端界面             |
| Gwt            | 2.8.2             | Google Web Tool  |
| Springboot     | 2.5.14            | 容器+MVC框架         |
| Druid          | 1.2.8             | 阿里数据库连接池         |
| Mybatis        | 2.2.2             | ORM框架            |
| Swagger-UI     | 3.0.0             | 文档生产工具           |
| Redis          | boot              | 分布式缓存            |
| SpringSecurity | boot              | 认证和授权框架          |
| JWT            | 0.9.1             | 鉴权               |

## 用到特殊内部类（用到的说明）

### 登录

调用流程  

待完善...


---


#### Security

##### UsernamePasswordAuthenticationToken 用户名和密码验证

```java
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {
    // 序列化id
    private static final long serialVersionUID = 550L;
    // 用户名
    private final Object principal;
    // 密码
    private Object credentials;

    /**
     * 构造
     *
     * @param principal 用户名
     * @param credentials 密码
     */
    public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    /**
     * 构造
     *
     * @param principal 用户名
     * @param credentials 密码
     * @param authorities 权限
     */
    public UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    /**
     * 获取用户名
     *
     * @return 用户名信息
     */
    public Object getCredentials() {
        return this.credentials;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * 设置是否已经认证
     *
     * @param isAuthenticated 是否通过验证
     */
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    /**
     * 清除凭证
     */
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
```

#### AuthenticationManager 认证相关的核心接口

#### Authentication 封装用户的验证请求信息

```java

```
