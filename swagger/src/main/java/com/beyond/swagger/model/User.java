package com.beyond.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/11/14
 */
@ApiModel(description = "学生对象")
public class User {

    @ApiModelProperty("学生id")
    private Integer id;

    @ApiModelProperty("学生姓名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final User instance = new User();

        private Builder() {}

        public Builder withId(Integer id) {
            this.instance.setId(id);
            return this;
        }

        public Builder withName(String name) {
            this.instance.setName(name);
            return this;
        }

        public User build() {
            return this.instance;
        }
    }
}
