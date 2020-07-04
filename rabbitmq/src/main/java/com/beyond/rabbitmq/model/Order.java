package com.beyond.rabbitmq.model;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hdb001
 * @date 2020/7/4 11:29
 */
public class Order implements Serializable {

    private int id;

    public Order() {
    }

    public Order(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
