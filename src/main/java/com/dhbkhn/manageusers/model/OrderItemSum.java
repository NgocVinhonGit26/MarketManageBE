package com.dhbkhn.manageusers.model;

import java.math.BigDecimal;

public class OrderItemSum {
    private int totalOrders;
    private BigDecimal sum;

    public OrderItemSum() {
    }

    public OrderItemSum(int totalOrders, BigDecimal sum) {
        this.totalOrders = totalOrders;
        this.sum = sum;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

}
