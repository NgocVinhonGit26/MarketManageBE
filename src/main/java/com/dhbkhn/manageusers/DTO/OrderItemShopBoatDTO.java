package com.dhbkhn.manageusers.DTO;

import java.math.BigDecimal;

public class OrderItemShopBoatDTO {
    private int id;
    private String status;
    private int productId;
    private int orderProductId;
    private int shopBoatId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal sale;
    private String userName;
    private String userNumberPhone;
    private String userAddress;

    public OrderItemShopBoatDTO() {
    }

    public OrderItemShopBoatDTO(int id, String status, int productId, int orderProductId, int shopBoatId, int quantity,
            BigDecimal price,
            BigDecimal sale, String userName, String userNumberPhone, String userAddress) {
        this.id = id;
        this.status = status;
        this.productId = productId;
        this.orderProductId = orderProductId;
        this.shopBoatId = shopBoatId;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getShopBoatId() {
        return shopBoatId;
    }

    public void setShopBoatId(int shopBoatId) {
        this.shopBoatId = shopBoatId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
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
