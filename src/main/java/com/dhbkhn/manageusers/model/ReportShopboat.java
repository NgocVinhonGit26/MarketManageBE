package com.dhbkhn.manageusers.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reportshopboat")
public class ReportShopboat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int shop_boat_id;
    private int user_id;
    private String description;
    private String imgrp;
    private String code_order_product;
    private String status;
    private Timestamp created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_boat_id() {
        return shop_boat_id;
    }

    public void setShop_boat_id(int shop_boat_id) {
        this.shop_boat_id = shop_boat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgrp() {
        return imgrp;
    }

    public void setImgrp(String imgrp) {
        this.imgrp = imgrp;
    }

    public String getCode_order_product() {
        return code_order_product;
    }

    public void setCode_order_product(String code_order_product) {
        this.code_order_product = code_order_product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
