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

        public Page<Tour> searchTour(
                        String name,
                        BigDecimal priceFrom,
                        BigDecimal priceTo,
                        String transport,
                        String startLocation,
                        String tourDuration,
                        int page);

        public void updateTourById(String name, String slug, String startTime, String startLocation,
                        String tourDuration, String description, BigDecimal price, String avatar, String transport,
                        String tourInformation, int id);

        // order tour
        // get order tour by id
        public OrderTour getOrderTourById(int id);

        public void insertOrder(int status, String paymentMethod, Timestamp startTime, int quantity, int tourId,
                        String tourName, int userId, Timestamp createAt);

        public Page<Object[]> searchOrderTour(String userName, String tourName, Integer status, int page);

        public String updateStatusOrder(int status, int orderId);

        // get quantity of order tour have status = 3 in today
        public Object getQuantityOrderTourStatus3InToday();

        // get quantity of order tour have status = 2 in today
        public int getQuantityOrderTourByStatus2InToday();

        // get quantity of order tour have status = 3 in this week
        public Object getQuantityOrderTourStatus3InThisWeek();

        // get quantity of order tour have status = 2 in this week
        public int getQuantityOrderTourByStatus2InThisWeek();

        // get quantity of order tour have status = 3 in this month
        public Object getQuantityOrderTourStatus3InThisMonth();

        // get quantity of order tour have status = 2 in this month
        public int getQuantityOrderTourByStatus2InThisMonth();

        // get quantity of order tour have status = 3 in this year
        public Object getQuantityOrderTourStatus3InThisYear();

        // get quantity of order tour have status = 2 in this year
        public int getQuantityOrderTourByStatus2InThisYear();

        // get quantity of order tour have status = 3 in 0h-3h, 3h-6h, 6h-9h, 9h-12h,
        // 12h-15h, 15h-18h, 18h-21h, 21h-24h
        public List<Object[]> getQuantityOrderTourCompleteTodayByTimePeriod();

        // get quantity of order tour have status = 3 in chủ nhật, thứ 2, thứ 3, thứ 4,
        // thứ 5, thứ 6, thứ 7
        public List<Object[]> getQuantityOrderTourCompleteThisWeekByDayOfWeek();

        // get quantity of order tour have status = 3 in Tuần 1, Tuần 2, Tuần 3, Tuần 4,
        // Tuần 5
        public List<Object[]> getQuantityOrderTourCompleteThisMonthByWeekOfMonth();

        // get quantity of order tour have status = 3 in T1, T2, T3, T4, T5, T6, T7, T8,
        // T9, T10, T11, T12
        public List<Object[]> getQuantityOrderTourCompleteThisYearByQuarter();

        // get top5 tour have price highest in today
        public List<Object[]> getTop5TourHighestPriceInToday();

        // get top5 tour have price highest in this week
        public List<Object[]> getTop5TourHighestPriceInThisWeek();

        // get top5 tour have price highest in this month
        public List<Object[]> getTop5TourHighestPriceInThisMonth();

        // get top5 tour have price highest in this year
        public List<Object[]> getTop5TourHighestPriceInThisYear();
}
