package com.dhbkhn.manageusers.model.Tour;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String slug;
    private String start_time;

    @Column(name = "start_location", nullable = false)
    private String start_location;

    @Column(name = "tour_duration", nullable = false)
    private String tour_duration;

    private String description;
    private BigDecimal price;
    private String avatar;
    private String transport;
    private String tour_information;

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

    public String getStartTime() {
        return start_time;
    }

    public void setStartTime(String startTime) {
        this.start_time = startTime;
    }

    public String getStartLocation() {
        return start_location;
    }

    public void setStartLocation(String startLocation) {
        this.start_location = startLocation;
    }

    public String getTourDuration() {
        return tour_duration;
    }

    public void setTourDuration(String tourDuration) {
        this.tour_duration = tourDuration;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTourInformation() {
        return tour_information;
    }

    public void setTourInformation(String tourInformation) {
        this.tour_information = tourInformation;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

}
