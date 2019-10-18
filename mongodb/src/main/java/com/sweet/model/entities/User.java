package com.sweet.model.entities;

import java.util.StringJoiner;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
public class User {

    private Long id;

    private String account;

    private String username;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("account='" + account + "'")
                .add("username='" + username + "'")
                .add("email='" + email + "'")
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private User instance = new User();

        private Builder() {}

        public Builder withId(Long id) {
            this.instance.setId(id);
            return this;
        }

        public Builder withAccount(String account) {
            this.instance.setAccount(account);
            return this;
        }

        public Builder withUsername(String username) {
            this.instance.setUsername(username);
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
