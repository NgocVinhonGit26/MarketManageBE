package com.dhbkhn.manageusers.model.Product;

import jakarta.persistence.Column;

// import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.dhbkhn.manageusers.model.ShopBoat;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String slug;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sale = BigDecimal.ZERO;

    @Column(nullable = false)
    private int count_in_stock = 0;

    @Column(length = 255)
    private String image;

    // Nếu bạn muốn thêm trường unit
    @Column(nullable = false, length = 255)
    private String unit;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "shopBoat_id", nullable = false)
    @Column(name = "shop_boat_id", nullable = false)
    private int shop_boat_id;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created_at;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updated_at;

    @Column(name = "video_infor", nullable = true)
    private String video_infor;

    @Column(name = "isdeleted")
    private Boolean isdeleted;

    // Getters và Setters

    public Product() {
    }

    public Product(String name, String slug, String description, BigDecimal price, BigDecimal sale, int count_in_stock,
            String image, String unit, String category, int shopBoatId) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.count_in_stock = count_in_stock;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.shop_boat_id = shopBoatId;
    }

    public Product(int id, String name, String slug, String description, BigDecimal price, BigDecimal sale,
            int count_in_stock, String image, String unit, String category, int shopBoatId, Timestamp created_at,
            Timestamp updated_at, String videoInfor) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.count_in_stock = count_in_stock;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.shop_boat_id = shopBoatId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.video_infor = videoInfor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getCountInStock() {
        return count_in_stock;
    }

    public void setCountInStock(int count_in_stock) {
        this.count_in_stock = count_in_stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getShopBoatId() {
        return shop_boat_id;
    }

    public void setShopBoatId(int shopBoatId) {
        this.shop_boat_id = shopBoatId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVideoInfor() {
        return video_infor;
    }

    public void setVideoInfor(String videoInfor) {
        this.video_infor = videoInfor;
    }

    public Boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}
