package com.dhbkhn.manageusers.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.Tour.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

        // get all tour
        List<Tour> findAll();

        // get tour by slug
        Optional<Tour> findBySlug(String slug);

        // create order tour
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO order_tour (status, payment_method, start_time, quantity, tour_id, user_id, price, created_at) "
                        + "VALUES (:status, :paymentMethod, :startTime, :quantity, :tourId, :userId, :price, :createAt)", nativeQuery = true)
        void insertOrder(@Param("status") String status,
                        @Param("paymentMethod") String paymentMethod,
                        @Param("startTime") Date startTime,
                        @Param("quantity") int quantity,
                        @Param("tourId") int tourId,
                        @Param("userId") int userId,
                        @Param("price") BigDecimal price,
                        @Param("createAt") Timestamp createAt);

}
