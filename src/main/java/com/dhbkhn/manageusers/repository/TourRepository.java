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

        // create tour
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tour (name, slug, start_time, start_location, tour_duration, description, price, avatar, transport, tour_information) "
                        + "VALUES (:name, :slug, :startTime, :startLocation, :tourDuration, :description, :price, :avatar, :transport, :tourInformation)", nativeQuery = true)
        void insertTour(@Param("name") String name, @Param("slug") String slug, @Param("startTime") String startTime,
                        @Param("startLocation") String startLocation, @Param("tourDuration") String tourDuration,
                        @Param("description") String description, @Param("price") BigDecimal price,
                        @Param("avatar") String avatar,
                        @Param("transport") String transport, @Param("tourInformation") String tourInformation);

        // update tour by id
        @Transactional
        @Modifying
        @Query(value = "UPDATE tour SET name = :name, slug = :slug, start_time = :startTime, "
                        + "start_location = :startLocation, tour_duration = :tourDuration, description = :description, "
                        + "price = :price, avatar = :avatar, transport = :transport, tour_information = :tourInformation WHERE id = :id", nativeQuery = true)
        void updateTour(@Param("name") String name, @Param("slug") String slug, @Param("startTime") String startTime,
                        @Param("startLocation") String startLocation, @Param("tourDuration") String tourDuration,
                        @Param("description") String description, @Param("price") BigDecimal price,
                        @Param("avatar") String avatar,
                        @Param("transport") String transport, @Param("tourInformation") String tourInformation,
                        @Param("id") int id);

        // get all tour with pagination
        // @Query(value = "SELECT * FROM tour", nativeQuery = true)
        // Page<Object[]> findAllTourCC(Pageable pageable);

        // get tour by slug
        Optional<Tour> findBySlug(String slug);

        // get order tour by id

        @Query(value = "SELECT ot FROM OrderTour ot WHERE ot.id = :id")
        public OrderTour getOrderTourById(@Param("id") int id);

        // create order tour
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO order_tour (status, payment_method, start_time, quantity, tour_id, tour_name, user_id, created_at) "
                        + "VALUES (:status, :paymentMethod, :startTime, :quantity, :tourId, :tourName, :userId, :createdAt)", nativeQuery = true)
        void insertOrder(@Param("status") int status,
                        @Param("paymentMethod") String paymentMethod,
                        @Param("startTime") Timestamp startTime,
                        @Param("quantity") int quantity,
                        @Param("tourId") int tourId,
                        @Param("tourName") String tourName,
                        @Param("userId") int userId,
                        @Param("createdAt") Timestamp createdAt);

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
                        "(:userName IS NULL OR u.name like %:userName%) AND " +
                        "(:tourName IS NULL OR t.name like %:tourName%) AND " +
                        "(:startTimeFrom IS NULL OR ot.start_time >= :startTimeFrom) AND " +
                        "(:startTimeTo IS NULL OR ot.start_time <= :startTimeTo) AND " +
                        "(:priceFrom IS NULL OR ot.price >= :priceFrom) AND " +
                        "(:priceTo IS NULL OR ot.price <= :priceTo) AND " +
                        "(:status IS NULL OR ot.status = :status) AND " +
                        "(:createdAtFrom IS NULL OR ot.created_at >= :createdAtFrom) AND " +
                        "(:createdAtTo IS NULL OR ot.created_at <= :createdAtTo) ", nativeQuery = true)

        public Page<Object[]> searchOrderTour(
                        @Param("userName") String userName,
                        @Param("tourName") String tourName,
                        @Param("startTimeFrom") Timestamp startTimeFrom,
                        @Param("startTimeTo") Timestamp startTimeTo,
                        @Param("priceFrom") BigDecimal priceFrom,
                        @Param("priceTo") BigDecimal priceTo,
                        @Param("status") Integer status,
                        @Param("createdAtFrom") Timestamp createdAtFrom,
                        @Param("createdAtTo") Timestamp createdAtTo,
                        Pageable pageable);

        // update status order tour
        @Transactional
        @Modifying
        @Query(value = "UPDATE order_tour SET status = :status WHERE id = :id", nativeQuery = true)
        void updateStatusOrder(@Param("status") int status, @Param("id") int id);

        // search Tour by name, price, transport, start_location, tour_duration
        @Query(value = "SELECT * FROM tour WHERE " +
                        "(:name IS NULL OR name like %:name%) AND " +
                        "(:priceFrom IS NULL OR price >= :priceFrom) AND " +
                        "(:priceTo IS NULL OR price <= :priceTo) AND " +
                        "(:transport IS NULL OR transport like %:transport%) AND " +
                        "(:startLocation IS NULL OR start_location like %:startLocation%) AND " +
                        "(:tourDuration IS NULL OR tour_duration like %:tourDuration%)", nativeQuery = true)
        public Page<Tour> searchTour(
                        @Param("name") String name,
                        @Param("priceFrom") BigDecimal priceFrom,
                        @Param("priceTo") BigDecimal priceTo,
                        @Param("transport") String transport,
                        @Param("startLocation") String startLocation,
                        @Param("tourDuration") String tourDuration,
                        Pageable pageable);

        // update tour by id
        @Transactional
        @Modifying
        @Query(value = "UPDATE tour t SET t.name = :name, t.slug = :slug, t.start_time = :startTime, t.start_location = :startLocation, "
                        +
                        "t.tour_duration = :tourDuration, t.description = :description, t.price = :price, t.avatar = :avatar, t.transport = :transport, "
                        +
                        "t.tour_information = :tourInformation WHERE t.id = :id", nativeQuery = true)
        public void updateTourById(
                        @Param("name") String name,
                        @Param("slug") String slug,
                        @Param("startTime") String startTime,
                        @Param("startLocation") String startLocation,
                        @Param("tourDuration") String tourDuration,
                        @Param("description") String description,
                        @Param("price") BigDecimal price,
                        @Param("avatar") String avatar,
                        @Param("transport") String transport,
                        @Param("tourInformation") String tourInformation,
                        @Param("id") int id);

        // get quantity of order tour have status = 3 in today
        @Query(value = "SELECT COUNT(*), SUM(price) FROM order_tour WHERE status = 3 AND DATE(created_at) = CURDATE()", nativeQuery = true)
        public Object getQuantityOrderTourCompleteInToday();

        // get quantity of order tour have status = 2 in today
        @Query(value = "SELECT COUNT(*) FROM order_tour WHERE status = 2 AND DATE(created_at) = CURDATE()", nativeQuery = true)
        public int getQuantityOrderTourCancelInToday();

        // get quantity of order tour have status = 3 in this week
        @Query(value = "SELECT COUNT(*), SUM(price) FROM order_tour WHERE status = 3 AND YEARWEEK(created_at) = YEARWEEK(NOW())", nativeQuery = true)
        public Object getQuantityOrderTourCompleteInThisWeek();

        // get quantity of order tour have status = 2 in this week
        @Query(value = "SELECT COUNT(*) FROM order_tour WHERE status = 2 AND YEARWEEK(created_at) = YEARWEEK(NOW())", nativeQuery = true)
        public int getQuantityOrderTourCancelInThisWeek();

        // get quantity of order tour have status = 3 in this month
        @Query(value = "SELECT COUNT(*), SUM(price) FROM order_tour WHERE status = 3 AND MONTH(created_at) = MONTH(NOW())", nativeQuery = true)
        public Object getQuantityOrderTourCompleteInThisMonth();

        // get quantity of order tour have status = 2 in this month
        @Query(value = "SELECT COUNT(*) FROM order_tour WHERE status = 2 AND MONTH(created_at) = MONTH(NOW())", nativeQuery = true)
        public int getQuantityOrderTourCancelInThisMonth();

        // get quantity of order tour have status = 3 in this year
        @Query(value = "SELECT COUNT(*), SUM(price) FROM order_tour WHERE status = 3 AND YEAR(created_at) = YEAR(NOW())", nativeQuery = true)
        public Object getQuantityOrderTourCompleteInThisYear();

        // get quantity of order tour have status = 2 in this year
        @Query(value = "SELECT COUNT(*) FROM order_tour WHERE status = 2 AND YEAR(created_at) = YEAR(NOW())", nativeQuery = true)
        public int getQuantityOrderTourCancelInThisYear();

        // get quantity of order tour have status = 3 in 0h-3h, 3h-6h, 6h-9h, 9h-12h,
        // 12h-15h, 15h-18h, 18h-21h, 21h-24h
        @Query(value = "SELECT " +
                        "CASE " +
                        "WHEN HOUR(created_at) BETWEEN 0 AND 2 THEN '0h-3h' " +
                        "WHEN HOUR(created_at) BETWEEN 3 AND 5 THEN '3h-6h' " +
                        "WHEN HOUR(created_at) BETWEEN 6 AND 8 THEN '6h-9h' " +
                        "WHEN HOUR(created_at) BETWEEN 9 AND 11 THEN '9h-12h' " +
                        "WHEN HOUR(created_at) BETWEEN 12 AND 14 THEN '12h-15h' " +
                        "WHEN HOUR(created_at) BETWEEN 15 AND 17 THEN '15h-18h' " +
                        "WHEN HOUR(created_at) BETWEEN 18 AND 20 THEN '18h-21h' " +
                        "WHEN HOUR(created_at) BETWEEN 21 AND 23 THEN '21h-24h' " +
                        "END AS time_period, " +
                        "COUNT(*) AS total_records, " +
                        "SUM(price) AS total_revenue " +
                        "FROM order_tour " +
                        "WHERE status = 3 " +
                        "AND DATE(created_at) = CURDATE() " +
                        "GROUP BY time_period " +
                        "ORDER BY time_period", nativeQuery = true)
        public List<Object[]> getQuantityOrderTourCompleteTodayByTimePeriod();

        // get quantity of order tour have status = 3 in chủ nhật, thứ 2, thứ 3, thứ 4,
        // thứ 5, thứ 6, thứ 7
        @Query(value = "SELECT " +
                        "CASE " +
                        "WHEN DAYOFWEEK(created_at) = 1 THEN 'Chủ nhật' " +
                        "WHEN DAYOFWEEK(created_at) = 2 THEN 'Thứ 2' " +
                        "WHEN DAYOFWEEK(created_at) = 3 THEN 'Thứ 3' " +
                        "WHEN DAYOFWEEK(created_at) = 4 THEN 'Thứ 4' " +
                        "WHEN DAYOFWEEK(created_at) = 5 THEN 'Thứ 5' " +
                        "WHEN DAYOFWEEK(created_at) = 6 THEN 'Thứ 6' " +
                        "WHEN DAYOFWEEK(created_at) = 7 THEN 'Thứ 7' " +
                        "END AS day_of_week, " +
                        "COUNT(*) AS total_records, " +
                        "SUM(price) AS total_revenue " +
                        "FROM order_tour " +
                        "WHERE status = 3 " +
                        "AND YEARWEEK(created_at) = YEARWEEK(NOW()) " +
                        "GROUP BY day_of_week " +
                        "ORDER BY day_of_week", nativeQuery = true)
        public List<Object[]> getQuantityOrderTourCompleteThisWeekByDayOfWeek();

        // get quantity of order tour have status = 3 in Tuần 1, Tuần 2, Tuần 3, Tuần 4,
        // Tuần 5
        @Query(value = "SELECT " +
                        "CONCAT('Tuần ', WEEK(created_at, 5) - WEEK(DATE_FORMAT(created_at, '%Y-%m-01'), 5) + 1) AS week_of_month, "
                        +
                        "COUNT(*) AS total_quantity, " +
                        "SUM(price) AS total_price " +
                        "FROM order_tour " +
                        "WHERE status = 3 " +
                        "AND MONTH(created_at) = MONTH(CURDATE()) " +
                        " AND YEAR(created_at) = YEAR(CURDATE()) " +
                        "GROUP BY week_of_month " +
                        "ORDER BY week_of_month", nativeQuery = true)
        public List<Object[]> getQuantityOrderTourCompleteThisMonthByWeekOfMonth();

        // get quantity of order tour have status = 3 in T1, T2, T3, T4, T5, T6, T7, T8,
        // T9, T10, T11, T12
        @Query(value = "SELECT " +
                        "CONCAT('T', MONTH(created_at)) AS time_period, " +
                        "COUNT(*) AS total_quantity, " +
                        "SUM(price) AS total_price " +
                        "FROM order_tour " +
                        "WHERE status = 3 " +
                        "AND YEAR(created_at) = YEAR(CURDATE()) " +
                        "GROUP BY time_period " +
                        "ORDER BY time_period", nativeQuery = true)
        public List<Object[]> getQuantityOrderTourCompleteThisYearByQuarter();

        // get top5 tour have price highest in today
        @Query(value = "SELECT " +
                        "t.name AS `Tên tour`, " +
                        "t.tour_duration AS `Thời gian tour`, " +
                        "COUNT(ot.id) AS `Tổng số đơn`, " +
                        "SUM(ot.price) AS `Tổng doanh thu` " +
                        "FROM order_tour ot " +
                        "INNER JOIN tour t ON ot.tour_id = t.id " +
                        "WHERE " +
                        "(ot.status = 3) " +
                        "AND DATE(ot.created_at) = CURDATE() " +
                        "GROUP BY t.id, t.name, t.tour_duration " +
                        "ORDER BY `Tổng doanh thu` DESC " +
                        "LIMIT 5", nativeQuery = true)
        public List<Object[]> getTop5TourHighestPriceInToday();

        // get top5 tour have price highest in this week
        @Query(value = "SELECT " +
                        "t.name AS `Tên tour`, " +
                        "t.tour_duration AS `Thời gian tour`, " +
                        "COUNT(ot.id) AS `Tổng số đơn`, " +
                        "SUM(ot.price) AS `Tổng doanh thu` " +
                        "FROM order_tour ot " +
                        "INNER JOIN tour t ON ot.tour_id = t.id " +
                        "WHERE " +
                        "(ot.status = 3) " +
                        "AND YEAR(ot.created_at) = YEAR(CURDATE()) " +
                        "AND WEEK(ot.created_at) = WEEK(CURDATE()) " +
                        "GROUP BY t.id, t.name, t.tour_duration " +
                        "ORDER BY `Tổng doanh thu` DESC " +
                        "LIMIT 5", nativeQuery = true)
        public List<Object[]> getTop5TourHighestPriceInThisWeek();

        // get top5 tour have price highest in this month
        @Query(value = "SELECT " +
                        "t.name AS `Tên tour`, " +
                        "t.tour_duration AS `Thời gian tour`, " +
                        "COUNT(ot.id) AS `Tổng số đơn`, " +
                        "SUM(ot.price) AS `Tổng doanh thu` " +
                        "FROM order_tour ot " +
                        "INNER JOIN tour t ON ot.tour_id = t.id " +
                        "WHERE " +
                        "(ot.status = 3) " +
                        "AND YEAR(ot.created_at) = YEAR(CURDATE()) " +
                        "AND MONTH(ot.created_at) = MONTH(CURDATE()) " +
                        "GROUP BY t.id, t.name, t.tour_duration " +
                        "ORDER BY `Tổng doanh thu` DESC " +
                        "LIMIT 5", nativeQuery = true)
        public List<Object[]> getTop5TourHighestPriceInThisMonth();

        // get top5 tour have price highest in this year
        @Query(value = "SELECT " +
                        "t.name AS `Tên tour`, " +
                        "t.tour_duration AS `Thời gian tour`, " +
                        "COUNT(ot.id) AS `Tổng số đơn`, " +
                        "SUM(ot.price) AS `Tổng doanh thu` " +
                        "FROM order_tour ot " +
                        "INNER JOIN tour t ON ot.tour_id = t.id " +
                        "WHERE " +
                        "(ot.status = 3) " +
                        "AND YEAR(ot.created_at) = YEAR(CURDATE()) " +
                        "GROUP BY t.id, t.name, t.tour_duration " +
                        "ORDER BY `Tổng doanh thu` DESC " +
                        "LIMIT 5", nativeQuery = true)
        public List<Object[]> getTop5TourHighestPriceInThisYear();

}
