package com.dhbkhn.manageusers.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhbkhn.manageusers.DTO.OrderTourDTO;
import com.dhbkhn.manageusers.model.Tour.OrderTour;
import com.dhbkhn.manageusers.model.Tour.Tour;
import com.dhbkhn.manageusers.service.Tour.TourService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tour")
public class TourController {
    private TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/getAllTour")
    public List<Tour> getAllTour() {
        return tourService.getAllTour();
    }

    @GetMapping("/getTourBySlug/{slug}")
    public Optional<Tour> getTourBySlug(@PathVariable String slug) {
        return tourService.getTourBySlug(slug);
    }

    @PostMapping("/insertOrder")
    public ResponseEntity<String> insertOrder(@RequestBody OrderTour request) {
        try {
            // System.out.println("hehe" + request.getStatus() + request.getPaymentMethod()
            // + request.getStartTime() +
            // request.getQuantity() + request.getTourId() + request.getUserId() +
            // request.getPrice());
            tourService.insertOrder(
                    request.getStatus(),
                    request.getPaymentMethod(),
                    request.getStartTime(),
                    request.getQuantity(),
                    request.getTourId(),
                    request.getUserId(),
                    new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
