package com.dhbkhn.manageusers.service.Tour;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dhbkhn.manageusers.model.Tour.OrderTour;
import com.dhbkhn.manageusers.model.Tour.Tour;
import com.dhbkhn.manageusers.repository.TourRepository;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAllTour() {
        // TODO Auto-generated method stub
        return tourRepository.findAll();
    }

    // insert tour
    @Override
    public void insertTour(String name, String slug, String startTime, String startLocation, String tourDuration,
            String description, BigDecimal price, String avatar, String transport, String tourInformation) {
        // TODO Auto-generated method stub
        tourRepository.insertTour(name, slug, startTime, startLocation, tourDuration, description, price, avatar,
                transport, tourInformation);
    }

    // @Autowired
    // public Page<Object[]> getAllTourWithPagination(int page) {
    // int pageSize = 5;
    // try {
    // Pageable pageable = PageRequest.of(page, pageSize);
    // return tourRepository.findAllTourCC(pageable);
    // } catch (Exception e) {
    // e.printStackTrace();
    // return Page.empty();
    // }
    // }

    // get Tour by slug
    @Override
    public Optional<Tour> getTourBySlug(String slug) {
        // TODO Auto-generated method stub
        return tourRepository.findBySlug(slug);
    }

    // search tour
    @Override
    public Page<Tour> searchTour(String name, BigDecimal priceFrom, BigDecimal priceTo, String transport,
            String startLocation, String tourDuration, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            System.out.println("TourServiceImpl: " + name + " " + priceFrom + " " + priceTo + " " + transport + " "
                    + startLocation + " " + tourDuration + " " + page);
            return tourRepository.searchTour(name, priceFrom, priceTo, transport, startLocation, tourDuration,
                    pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    // updateTourById
    @Override
    public void updateTourById(String name, String slug, String startTime, String startLocation, String tourDuration,
            String description, BigDecimal price, String avatar, String transport, String tourInformation, int id) {
        // TODO Auto-generated method stub
        tourRepository.updateTourById(name, slug, startTime, startLocation, tourDuration, description, price, avatar,
                transport, tourInformation, id);
    }

    // ---------------ORDER TOUR

    // insert order
    @Override
    public void insertOrder(int status, String paymentMethod, Timestamp startTime, int quantity, int tourId,
            String tourName,
            int userId, Timestamp createAt) {
        // TODO Auto-generated method stub
        tourRepository.insertOrder(status, paymentMethod, startTime, quantity, tourId, tourName, userId, createAt);
    }

    // search order tour
    @Override
    public Page<Object[]> searchOrderTour(String userName, String tourName, Timestamp startTimeFrom,
            Timestamp startTimeTo, BigDecimal priceFrom, BigDecimal priceTo, Integer status,
            Timestamp createdAtFrom, Timestamp createdAtTo, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return tourRepository.searchOrderTour(userName, tourName, startTimeFrom, startTimeTo, priceFrom, priceTo,
                    status, createdAtFrom, createdAtTo, pageable);
        } catch (Exception e) {
            // System.out.println("tat ca chi la giac mo hehe");
            e.printStackTrace();
            return Page.empty();
        }
    }

    // get order tour by id
    @Override
    public OrderTour getOrderTourById(int id) {
        return tourRepository.getOrderTourById(id);
    }

    // update status order
    @Override
    public String updateStatusOrder(int status, int orderId) {
        // TODO Auto-generated method stub
        try {
            tourRepository.updateStatusOrder(status, orderId);
            return "Update status successfully!";
        } catch (Exception e) {
            System.out.println("Shopboat: update status failed!");
            return "Update status failed!";
        }
    }

    // get quantity of order tour have status = 3 in today
    @Override
    public Object getQuantityOrderTourStatus3InToday() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteInToday();
    }

    // get quantity of order tour have status = 2 in today
    @Override
    public int getQuantityOrderTourByStatus2InToday() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCancelInToday();
    }

    // get quantity of order tour have status = 3 in this week
    @Override
    public Object getQuantityOrderTourStatus3InThisWeek() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteInThisWeek();
    }

    // get quantity of order tour have status = 2 in this week
    @Override
    public int getQuantityOrderTourByStatus2InThisWeek() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCancelInThisWeek();
    }

    // get quantity of order tour have status = 3 in this month
    @Override
    public Object getQuantityOrderTourStatus3InThisMonth() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteInThisMonth();
    }

    // get quantity of order tour have status = 2 in this month
    @Override
    public int getQuantityOrderTourByStatus2InThisMonth() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCancelInThisMonth();
    }

    // get quantity of order tour have status = 3 in this year
    @Override
    public Object getQuantityOrderTourStatus3InThisYear() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteInThisYear();
    }

    // get quantity of order tour have status = 2 in this year
    @Override
    public int getQuantityOrderTourByStatus2InThisYear() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCancelInThisYear();
    }

    // get quantity of order tour have status = 3 in 0h-3h, 3h-6h, 6h-9h, 9h-12h,
    // 12h-15h, 15h-18h, 18h-21h, 21h-24h
    @Override
    public List<Object[]> getQuantityOrderTourCompleteTodayByTimePeriod() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteTodayByTimePeriod();
    }

    // get quantity of order tour have status = 3 in chủ nhật, thứ 2, thứ 3, thứ 4,
    // thứ 5, thứ 6, thứ 7
    @Override
    public List<Object[]> getQuantityOrderTourCompleteThisWeekByDayOfWeek() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteThisWeekByDayOfWeek();
    }

    // get quantity of order tour have status = 3 in Tuần 1, Tuần 2, Tuần 3, Tuần 4,
    // Tuần 5
    @Override
    public List<Object[]> getQuantityOrderTourCompleteThisMonthByWeekOfMonth() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteThisMonthByWeekOfMonth();
    }

    // get quantity of order tour have status = 3 in T1, T2, T3, T4, T5, T6, T7, T8,
    // T9, T10, T11, T12
    @Override
    public List<Object[]> getQuantityOrderTourCompleteThisYearByQuarter() {
        // TODO Auto-generated method stub
        return tourRepository.getQuantityOrderTourCompleteThisYearByQuarter();
    }

    // get top5 tour have price highest in today
    @Override
    public List<Object[]> getTop5TourHighestPriceInToday() {
        // TODO Auto-generated method stub
        return tourRepository.getTop5TourHighestPriceInToday();
    }

    // get top5 tour have price highest in this week
    @Override
    public List<Object[]> getTop5TourHighestPriceInThisWeek() {
        // TODO Auto-generated method stub
        return tourRepository.getTop5TourHighestPriceInThisWeek();
    }

    // get top5 tour have price highest in this month
    @Override
    public List<Object[]> getTop5TourHighestPriceInThisMonth() {
        // TODO Auto-generated method stub
        return tourRepository.getTop5TourHighestPriceInThisMonth();
    }

    // get top5 tour have price highest in this year
    @Override
    public List<Object[]> getTop5TourHighestPriceInThisYear() {
        // TODO Auto-generated method stub
        return tourRepository.getTop5TourHighestPriceInThisYear();
    }
}
