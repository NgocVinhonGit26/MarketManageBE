package com.dhbkhn.manageusers.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderProductDTO {
    private int id;
    private String status;
    private String paymentMethod;
    private BigDecimal total;
    private int customer;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String userName;
    private String userNumberPhone;
    private String userAddress;

    public OrderProductDTO() {
    }

    public OrderProductDTO(int id, String status, String paymentMethod, BigDecimal total, int customer,
            Timestamp createdAt, Timestamp updatedAt, String userName, String userNumberPhone, String userAddress) {
        this.id = id;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.customer = customer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
        this.userNumberPhone = userNumberPhone;
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumberPhone() {
        return userNumberPhone;
    }

    public void setUserNumberPhone(String userNumberPhone) {
        this.userNumberPhone = userNumberPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

}
