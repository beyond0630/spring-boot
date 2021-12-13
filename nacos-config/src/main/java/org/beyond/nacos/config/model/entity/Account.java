package org.beyond.nacos.config.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "bnc_account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户Id
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 余额(单位:分)
     */
    @Column(name = "balance", nullable = false)
    private String balance;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 用户Id
     */
    public Account setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 余额(单位:分)
     */
    public Account setBalance(String balance) {
        this.balance = balance;
        return this;
    }

    /**
     * 余额(单位:分)
     */
    public String getBalance() {
        return balance;
    }

    public Account setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id + '\'' +
            "userId=" + userId + '\'' +
            "balance=" + balance + '\'' +
            "disabled=" + disabled + '\'' +
            '}';
    }

}
