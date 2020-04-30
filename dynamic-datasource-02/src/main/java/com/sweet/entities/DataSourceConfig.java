package com.sweet.entities;

import java.io.Serializable;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public class DataSourceConfig implements Serializable {

    private Integer id;

    private String address;

    private Integer port;

    private String dataBase;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(final String dataBase) {
        this.dataBase = dataBase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String buildUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false",
                this.getAddress(), this.getPort(), this.getDataBase());
    }
}
