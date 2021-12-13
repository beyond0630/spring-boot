package org.beyond.nacos.config.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "bnc_pay_record")
public class PayRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "pay_money", nullable = false)
    private String payMoney;

    @Column(name = "pay_time", nullable = false)
    private Date payTime;

    public PayRecord setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public PayRecord setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public PayRecord setPayMoney(String payMoney) {
        this.payMoney = payMoney;
        return this;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public PayRecord setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }

    public Date getPayTime() {
        return payTime;
    }

    @Override
    public String toString() {
        return "PayRecord{" +
                "id=" + id + '\'' +
                "accountId=" + accountId + '\'' +
                "payMoney=" + payMoney + '\'' +
                "payTime=" + payTime + '\'' +
                '}';
    }
}
