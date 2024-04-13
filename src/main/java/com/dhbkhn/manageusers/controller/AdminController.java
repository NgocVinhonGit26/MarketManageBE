package com.dhbkhn.manageusers.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.Tour.OrderTour;
import com.dhbkhn.manageusers.model.Tour.Tour;
import com.dhbkhn.manageusers.service.ShopBoat.ShopBoatService;
import com.dhbkhn.manageusers.service.Tour.TourService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    private ShopBoatService shopBoatService;
    private TourService tourService;

    @Autowired
    public AdminController(ShopBoatService shopBoatService, TourService tourService) {
        this.shopBoatService = shopBoatService;
        this.tourService = tourService;
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    // update status by id
    @PostMapping("/updateStatusById/{id}")
    public ResponseEntity<ShopBoat> updateStatusById(@PathVariable int id, @RequestBody ShopBoat shopBoat) {
        shopBoatService.updateStatusById(shopBoat.getStatus(), id);
        ShopBoat updatedShopBoat = shopBoatService.getShopBoatById(id);
        if (updatedShopBoat != null) {
            System.out.println("Shopboat: update status successfully!");
            return ResponseEntity.ok(updatedShopBoat);
        }
        System.out.println("Shopboat: update status failed!");
        return ResponseEntity.notFound().build();

    }

    // manage shop boat
    @GetMapping("/getListShopBoats/{page}")
    public List<ShopBoatDTO> searchShopBoat(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer status,
            @PathVariable int page) {
        List<ShopBoatDTO> listShopBoatDTOs = new ArrayList<>();
        Page<Object[]> pageResult = shopBoatService.searchShopBoat(name, code, phoneNumber, type, status, page);
        for (Object[] row : pageResult.getContent()) {
            ShopBoatDTO shopBoatDTO = new ShopBoatDTO();
            shopBoatDTO.setId((int) row[0]);
            shopBoatDTO.setName((String) row[1]);
            shopBoatDTO.setAddress((String) row[2]);
            // shopBoatDTO.setOwner((int)row[3]);
            shopBoatDTO.setDescription((String) row[4]);
            shopBoatDTO.setAvatar((String) row[5]);
            shopBoatDTO.setPhoneNumber((String) row[6]);
            shopBoatDTO.setType((String) row[7]);
            shopBoatDTO.setStatus((int) row[8]);
            shopBoatDTO.setCode((String) row[9]);
            shopBoatDTO.setOwnerName((String) row[10]);
            listShopBoatDTOs.add(shopBoatDTO);
        }
        return listShopBoatDTOs;
    }

    // get Total Page of shop boat
    @GetMapping("/getTotalPageShopBoat/{page}")
    public int getTotalPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer status,
            @PathVariable int page) {
        Page<Object[]> pageResult = shopBoatService.searchShopBoat(name, code, phoneNumber, type, status, page);
        return pageResult.getTotalPages();
    }

    // manage order tour
    @GetMapping("/getListOrderTour/{page}")
    public List<OrderTourDTO> searchOrderTour(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String tourName,
            @RequestParam(required = false) Integer status,
            @PathVariable int page) {
        List<OrderTourDTO> listOrderTourDTOs = new ArrayList<>();

        Page<Object[]> pageResult = tourService.searchOrderTour(userName, tourName, status, page);
        System.out.println("tat ca chi la giac mo " + pageResult.getContent());
        for (Object[] row : pageResult.getContent()) {
            System.out.println("rosss: " + row[1] + " " + row[2] + " " + row[3] + " " +
                    row[4] + " " + row[5] + " "
                    + row[6] + " " + row[7] + " " + row[8] + " " + row[9] + " " + row[10] + " " + row[11] + " "
                    + row[12] + " " + row[13] + " " + row[14] + " " + row[15] + " " + row[16] + " "
                    + row[17] + " " + row[18] + " " + row[19]);
            OrderTourDTO orderTourDTO = new OrderTourDTO();
            orderTourDTO.setId((int) row[0]);
            orderTourDTO.setStatus((int) row[1]);
            orderTourDTO.setPaymentMethod((String) row[2]);
            orderTourDTO.setStartTime((Date) row[3]);
            orderTourDTO.setQuantity((int) row[4]);
            orderTourDTO.setTourId((int) row[5]);
            orderTourDTO.setTourName((String) row[6]);
            orderTourDTO.setUserId((int) row[7]);
            orderTourDTO.setPrice((BigDecimal) row[8]);
            orderTourDTO.setCreateAt((Date) row[9]);
            orderTourDTO.setUserName((String) row[10]);
            orderTourDTO.setUserUName((String) row[11]);
            orderTourDTO.setUserAddress((String) row[12]);
            orderTourDTO.setUserNumberPhone((String) row[13]);
            orderTourDTO.setTourName((String) row[14]);
            orderTourDTO.setTourPrice((BigDecimal) row[15]);
            orderTourDTO.setTransport((String) row[16]);
            orderTourDTO.setAvatar((String) row[17]);
            orderTourDTO.setStartLocation((String) row[18]);
            orderTourDTO.setDuration((String) row[19]);
            listOrderTourDTOs.add(orderTourDTO);
        }
        return listOrderTourDTOs;
    }

    @GetMapping("/getTotalPageOrderTour/{page}")
    public int getTotalPageOrderTour(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String tourName,
            @RequestParam(required = false) Integer status,
            @PathVariable int page) {
        Page<Object[]> pageResult = tourService.searchOrderTour(userName, tourName, status, page);
        return pageResult.getTotalPages();
        // return pageResult;
    }

    @GetMapping("/getListTour/{page}")
    public List<Tour> searchTour(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo,
            @RequestParam(required = false) String transport,
            @RequestParam(required = false) String startLocation,
            @RequestParam(required = false) String tourDuration,
            @PathVariable int page) {
        Page<Tour> pageResult = tourService.searchTour(name, priceFrom, priceTo, transport,
                startLocation, tourDuration, page);
        List<Tour> listTour = new ArrayList<>();
        for (Tour row : pageResult.getContent()) {
            Tour tour = new Tour();
            tour.setId(row.getId());
            tour.setName(row.getName());
            tour.setSlug(row.getSlug());
            tour.setPrice(row.getPrice());
            tour.setTransport(row.getTransport());
            tour.setStartLocation(row.getStartLocation());
            tour.setStartTime(row.getStartTime());
            tour.setDescription(row.getDescription());
            tour.setTourInformation(row.getTourInformation());
            tour.setTourDuration(row.getTourDuration());
            tour.setAvatar(row.getAvatar());
            listTour.add(tour);
        }
        return listTour;
    }

    @GetMapping("/getTotalPageTour/{page}")
    public int getTotalPageTour(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo,
            @RequestParam(required = false) String transport,
            @RequestParam(required = false) String startLocation,
            @RequestParam(required = false) String tourDuration,
            @PathVariable int page) {
        Page<Tour> pageResult = tourService.searchTour(name, priceFrom, priceTo, transport,
                startLocation, tourDuration, page);
        return pageResult.getTotalPages();
    }

    @GetMapping("/getOrderTourById/{id}")
    public OrderTour getOrderTourById(@PathVariable int id) {
        return tourService.getOrderTourById(id);
    }

    // update status order
    @PostMapping("/updateStatusOrderById/{id}")
    public ResponseEntity<OrderTour> updateStatusOrderById(@PathVariable int id, @RequestBody OrderTour orderTour) {
        tourService.updateStatusOrder(orderTour.getStatus(), id);
        OrderTour updatedOrderTour = tourService.getOrderTourById(id);
        if (updatedOrderTour != null) {
            System.out.println("Shopboat: update status successfully!");
            return ResponseEntity.ok(updatedOrderTour);
        }
        System.out.println("Shopboat: update status failed!");
        return ResponseEntity.notFound().build();
    }

}