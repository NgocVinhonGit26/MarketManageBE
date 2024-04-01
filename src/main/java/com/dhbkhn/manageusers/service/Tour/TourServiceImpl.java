package com.dhbkhn.manageusers.service.Tour;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // get Tour by slug
    @Override
    public Optional<Tour> getTourBySlug(String slug) {
        // TODO Auto-generated method stub
        return tourRepository.findBySlug(slug);
    }

    // insert order
    @Override
    public void insertOrder(String status, String paymentMethod, Date startTime, int quantity, int tourId,
            int userId, BigDecimal price, Timestamp createAt) {
        // TODO Auto-generated method stub
        tourRepository.insertOrder(status, paymentMethod, startTime, quantity, tourId, userId, price, createAt);
    }

}
