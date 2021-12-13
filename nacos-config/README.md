# nacos-config

> 此 demo 主要演示了 Spring Boot 如何集成 nacos config, 使用 nacos 配置中心.

# 接入步骤

## 按实际情况修改 bootstrap.yml 中 nacos 的地址
```yaml
spring:
  cloud:
    nacos:
      config:
        server-addr: localhost:8848
```

## 在 nacos 添加 nacos-config.yaml

* data id 取名为: ${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
* -${spring.profiles.active} 当 spring.profiles.active=default 时, 可省略
```yaml
config:
  env: local

server:
  port: 8081  

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/beyond-nacos-config?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: beyond
  jpa:
    show-sql: true

logging:
  level:
    root: info
    com.beyond.event.driven: debug
    org.hibernate.type.descriptor.sql.BasicBinder: trace  
```