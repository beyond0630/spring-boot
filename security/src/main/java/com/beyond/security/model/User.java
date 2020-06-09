package com.beyond.security.model;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/29
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String avatarUrl;

    private String mobile;

    private String roles;

    private Byte status;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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


        public Builder withUsername(String username) {
            this.instance.setUsername(username);
            return this;
        }

        public Builder withPassword(String password) {
            this.instance.setPassword(password);
            return this;
        }

        public Builder withAvatarUrl(String avatarUrl) {
            this.instance.setAvatarUrl(avatarUrl);
            return this;
        }

        public Builder withMobile(String mobile) {
            this.instance.setMobile(mobile);
            return this;
        }

        public Builder withRoles(String roles) {
            this.instance.setRoles(roles);
            return this;
        }

        public Builder withStatus(Byte status) {
            this.instance.setStatus(status);
            return this;
        }

        public Builder withCreateTime(Date createTime) {
            this.instance.setCreateTime(createTime);
            return this;
        }

        public Builder withModifyTime(Date modifyTime) {
            this.instance.setModifyTime(modifyTime);
            return this;
        }

        public User build() {
            return this.instance;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("avatarUrl='" + avatarUrl + "'")
                .add("mobile='" + mobile + "'")
                .add("roles='" + roles + "'")
                .add("status=" + status)
                .add("createTime=" + createTime)
                .add("modifyTime=" + modifyTime)
                .toString();
    }
}
