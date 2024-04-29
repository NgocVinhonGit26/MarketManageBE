package com.dhbkhn.manageusers.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {
    private int id;
    private int productId;
    private int orderProductId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal sale;
    private String productAvatar;
    private String productName;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int id, int productId, int orderProductId, int quantity, BigDecimal price, BigDecimal sale,
            String productAvatar, String productName) {
        this.id = id;
        this.productId = productId;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.productAvatar = productAvatar;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProductAvatar() {
        return productAvatar;
    }

    public void setProductAvatar(String productAvatar) {
        this.productAvatar = productAvatar;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
