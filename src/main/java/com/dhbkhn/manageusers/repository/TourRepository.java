package com.dhbkhn.manageusers.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.Tour.OrderTour;
import com.dhbkhn.manageusers.model.Tour.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

        // get all tour
        List<Tour> findAll();

        // get tour by slug
        Optional<Tour> findBySlug(String slug);

        // get order tour by id

        @Query(value = "SELECT ot FROM OrderTour ot WHERE ot.id = :id")
        public OrderTour getOrderTourById(@Param("id") int id);

        // create order tour
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO order_tour (status, payment_method, start_time, quantity, tour_id, tour_name, user_id, created_at) "
                        + "VALUES (:status, :paymentMethod, :startTime, :quantity, :tourId, :tourName, :userId, :createAt)", nativeQuery = true)
        void insertOrder(@Param("status") int status,
                        @Param("paymentMethod") String paymentMethod,
                        @Param("startTime") Date startTime,
                        @Param("quantity") int quantity,
                        @Param("tourId") int tourId,
                        @Param("tourName") String tourName,
                        @Param("userId") int userId,
                        @Param("createAt") Timestamp createAt);

        // search order tour by username, tourname,...
        @Query(value = "SELECT ot.*, " +
                        "u.name AS user_name, u.username AS user_uname, u.address AS user_address, u.phone_number AS user_phone, "
                        +
                        "t.name AS tour_name , t.Price AS tour_price, t.transport AS transport, t.avatar AS avatar, " +
                        "t.start_location AS start_location, t.tour_duration AS duration "
                        +
                        "FROM order_tour ot " +
                        "INNER JOIN user u ON ot.user_id = u.id " +
                        "INNER JOIN tour t ON ot.tour_id = t.id " +
                        "WHERE " +
                        "(:userName IS NULL OR u.name = :userName) AND " +
                        "(:tourName IS NULL OR t.name = :tourName) AND " +
                        "(:status IS NULL OR ot.status = :status) ", nativeQuery = true)

        public Page<Object[]> searchOrderTour(
                        @Param("userName") String userName,
                        @Param("tourName") String tourName,
                        @Param("status") Integer status,
                        Pageable pageable);

        // update status order tour
        @Transactional
        @Modifying
        @Query(value = "UPDATE order_tour SET status = :status WHERE id = :id", nativeQuery = true)
        void updateStatusOrder(@Param("status") int status, @Param("id") int id);

}
