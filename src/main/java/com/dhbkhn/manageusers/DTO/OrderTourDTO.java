package com.dhbkhn.manageusers.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderTourDTO {
    private int id;
    private int status;
    private String paymentMethod;
    private Timestamp startTime;
    private int quantity;
    private int tourId;
    private int userId;
    private BigDecimal price;
    private Timestamp createAt;
    private String userName;
    private String userUName;
    private String userAddress;
    private String userNumberPhone;
    private String tourName;
    private BigDecimal tourPrice;
    private String transport;
    private String avatar;
    private String startLocation;
    private String duration;

    public OrderTourDTO() {
    }

    public OrderTourDTO(int id, int status, String paymentMethod, Timestamp startTime, int quantity, int tourId,
            int userId, BigDecimal price, Timestamp createAt, String userName, String userUName, String userAddress,
            String userNumberPhone, String tourName, BigDecimal tourPrice, String transport, String avatar,
            String startLocation, String duration) {
        super();
        this.id = id;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.startTime = startTime;
        this.quantity = quantity;
        this.tourId = tourId;
        this.userId = userId;
        this.price = price;
        this.createAt = createAt;
        this.userName = userName;
        this.userUName = userUName;
        this.userAddress = userAddress;
        this.userNumberPhone = userNumberPhone;
        this.tourName = tourName;
        this.tourPrice = tourPrice;
        this.transport = transport;
        this.avatar = avatar;
        this.startLocation = startLocation;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUName() {
        return userUName;
    }

    public void setUserUName(String userUName) {
        this.userUName = userUName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserNumberPhone() {
        return userNumberPhone;
    }

    public void setUserNumberPhone(String userNumberPhone) {
        this.userNumberPhone = userNumberPhone;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
