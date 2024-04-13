package com.dhbkhn.manageusers.service.Tour;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dhbkhn.manageusers.model.Tour.OrderTour;
import com.dhbkhn.manageusers.model.Tour.Tour;

public interface TourService {
        public List<Tour> getAllTour();

        // public Page<Object[]> getAllTourWithPagination(int page);

        public Optional<Tour> getTourBySlug(String slug);

        public void insertOrder(int status, String paymentMethod, Date date, int quantity, int tourId, String tourName,
                        int userId, Timestamp createAt);

        public Page<Object[]> searchOrderTour(String userName, String tourName, Integer status, int page);

        // get order tour by id
        public OrderTour getOrderTourById(int id);

        public Page<Tour> searchTour(
                        String name,
                        BigDecimal priceFrom,
                        BigDecimal priceTo,
                        String transport,
                        String startLocation,
                        String tourDuration,
                        int page);

        public String updateStatusOrder(int status, int orderId);

}
