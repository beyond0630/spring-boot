package com.beyond.dubbo.model;

import java.io.Serializable;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/25
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private User instance = new User();

        private Builder() {}

        public Builder withId(Integer id) {
            this.instance.setId(id);
            return this;
        }

        public Builder withName(String name) {
            this.instance.setName(name);
            return this;
        }

        public Builder withEmail(String email) {
            this.instance.setEmail(email);
            return this;
        }

        public User build() {
            return this.instance;
        }
    }
}
