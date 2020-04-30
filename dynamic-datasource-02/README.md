## **`项目简介`**
`dynamic-datasource-02` 采用 `SpringBoot` 集成 `Druid` 和 `Mybatis` 实现主从数据源动态切换，数据源从主库的表中获取，
启动时初始化主库数据源，其它从库数据源在需要使用时才初始化