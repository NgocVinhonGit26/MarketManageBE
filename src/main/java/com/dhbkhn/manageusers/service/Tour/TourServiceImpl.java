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
    public void insertOrder(int status, String paymentMethod, Date startTime, int quantity, int tourId, String tourName,
            int userId, Timestamp createAt) {
        // TODO Auto-generated method stub
        tourRepository.insertOrder(status, paymentMethod, startTime, quantity, tourId, tourName, userId, createAt);
    }

    // search order tour
    @Override
    public Page<Object[]> searchOrderTour(String userName, String tourName, Integer status, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return tourRepository.searchOrderTour(userName, tourName, status, pageable);
        } catch (Exception e) {
            System.out.println("tat ca chi la giac mo hehe");
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

}
