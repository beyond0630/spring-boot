package com.sweet.entities;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public class User {

    private Integer id;

    private String name;

    private String nickName;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }
}
