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
@Table(name = "Product")
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
    private int countInStock = 0;

    @Column(length = 255)
    private String image;

    // Nếu bạn muốn thêm trường unit
    @Column(nullable = false, length = 255)
    private String unit;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "shopBoat_id", nullable = false)
    @Column(name = "shopBoat_id", nullable = false)
    private int shopBoatId;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created_at;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updated_at;

    // Getters và Setters

    public Product() {
    }

    public Product(String name, String slug, String description, BigDecimal price, BigDecimal sale, int countInStock,
            String image, String unit, String category, int shopBoatId) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.countInStock = countInStock;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.shopBoatId = shopBoatId;
    }

    public Product(int id, String name, String slug, String description, BigDecimal price, BigDecimal sale,
            int countInStock, String image, String unit, String category, int shopBoatId, Timestamp created_at,
            Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.countInStock = countInStock;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.shopBoatId = shopBoatId;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
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
        return shopBoatId;
    }

    public void setShopBoatId(int shopBoatId) {
        this.shopBoatId = shopBoatId;
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

}
