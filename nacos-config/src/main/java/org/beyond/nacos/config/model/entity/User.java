package org.beyond.nacos.config.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "bnc_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 邮箱
     */
    @Column(name = "e_mail", nullable = false)
    private String eMail;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 用户名
     */
    public User setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 邮箱
     */
    public User setEMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    /**
     * 邮箱
     */
    public String getEMail() {
        return eMail;
    }

    public User setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "eMail=" + eMail + '\'' +
                "disabled=" + disabled + '\'' +
                '}';
    }
}
