package com.dhbkhn.manageusers.service.Tour;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.dhbkhn.manageusers.model.Tour.Tour;

public interface TourService {
    public List<Tour> getAllTour();

    public Optional<Tour> getTourBySlug(String slug);

    public void insertOrder(String status, String paymentMethod, Date date, int quantity, int tourId,
            int userId, BigDecimal price, Timestamp createAt);
}
