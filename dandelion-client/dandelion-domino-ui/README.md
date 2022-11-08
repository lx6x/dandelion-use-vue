# dandelion-domino-ui -- domino ui 页面

## 模块结构

```
    .../dandelion-domino-ui
        ../dandelion-domino-ui-client           页面
        ../dandelion-domino-ui-server           服务端
        ../dandelion-domino-ui-shared           中间模块
```

## 模块服务端口

```
    .../dandelion-domino-ui
        ../dandelion-domino-ui-client          8888 [注：默认端口运行可修改]
        ../dandelion-domino-ui-server          30005
        ../dandelion-domino-ui-shared 

```

### dandelion-domino-ui-client

* ui 页面
* 运行
    * idea下载插件 GWT；
    * 添加 webapp 文件，可直接复制 server 中 webapp，在其中添加 .html 文件；
    * 复制 module.gwt.xml 文件到 org.xxx.xxx.client 下，并使其与 client 包同级
    * 修改 client -> pom.xml 文件中 [ gwt-dev ] 的引入，排除其 apache-jsp，例如：
  ```
      <dependency>
         <groupId>com.google.gwt</groupId>
         <artifactId>gwt-dev</artifactId>
         <exclusions>
             <exclusion>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>apache-jsp</artifactId>
             </exclusion>
         </exclusions>
      </dependency>
  ```
    * Add Configuration -> Add New Configuration -> GWT Configuration -> Module 选中 client 模块；
    * run
    * 访问 -> http://localhost:8888/xxx.html [注：默认端口运行可修改]

### dandelion-domino-ui-server

* 服务端
* 运行
    * springboot 运行
* 部署
    * package 打包
    * java -jar 运行 server -> target 下 —> xxxx.war
    * 访问 -> http://localhost:8080/xxx.html [注：默认端口运行可修改]

### dandelion-domino-ui-shared

* 关联 ui 与 server，相当于中间层





