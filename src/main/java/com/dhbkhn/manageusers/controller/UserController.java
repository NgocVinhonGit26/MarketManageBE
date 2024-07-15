package com.dhbkhn.manageusers.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhbkhn.manageusers.DTO.BillDetail;
import com.dhbkhn.manageusers.model.ReportShopboat;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.service.Product.ProductService;
import com.dhbkhn.manageusers.service.ReportShopboat.ReportShopboatService;
import com.dhbkhn.manageusers.service.User.UserService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "https://market-manage-fe.vercel.app")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private ProductService productService;
    private ReportShopboatService reportShopboatService;

    @Autowired
    public UserController(UserService userService, ProductService productService,
            ReportShopboatService reportShopboatService) {

        this.userService = userService;
        this.productService = productService;
        this.reportShopboatService = reportShopboatService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User added successfully";
    }

    @GetMapping("/getListUser")
    public List<User> getListUser() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        User userResult = new User();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setAddress(user.getAddress());
        userResult.setAvatar(user.getAvatar());
        userResult.setPhoneNumber(user.getPhoneNumber());
        userResult.setUsername(user.getUsername());
        userResult.setRole(user.getRole());
        return userResult;

    }

    @GetMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PostMapping("/auth/registerUser")
    public User registerUser(@RequestBody User user) {
        User registerUser = userService.registerUser(user);
        if (registerUser == null) {
            System.out.println("Register failed, user already exists!");
            return null;
        }
        return registerUser;
    }

    @PostMapping("/login")
    public User authenticate(@RequestBody User user) {
        return userService.authenticate(user);
    }

    @GetMapping("/{id}/shopboat")
    public List<ShopBoat> getShopBoatByUserId(@PathVariable int id) {
        return userService.getShopBoatByUserId(id);
    }

    @PostMapping("/updateUserById/{id}")
    public ResponseEntity<User> updateUserById(
            @RequestParam String name,
            @RequestParam String avatar,
            @RequestParam String phone_number,
            @PathVariable int id) {
        userService.updateUserById(name, avatar, phone_number, id);
        User user = userService.getUserById(id);
        User userResult = new User();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setAddress(user.getAddress());
        userResult.setAvatar(user.getAvatar());
        userResult.setPhoneNumber(user.getPhoneNumber());
        userResult.setUsername(user.getUsername());
        userResult.setRole(user.getRole());
        return ResponseEntity.ok(userResult);

    }

    @GetMapping("/getOrderProductByCustomer/{customerId}")
    public List<Object[]> getOrderProductByCustomer(@PathVariable int customerId) {
        List<Object[]> resultOrderProduct = productService.getOrderProductByCustomer(customerId);
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Object[] object : resultOrderProduct) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setId((int) object[0]);
            orderProduct.setStatus((String) object[1]);
            orderProduct.setPaymentMethod((String) object[2]);
            orderProduct.setTotal((BigDecimal) object[3]);
            orderProduct.setCustomer((int) object[4]);
            orderProduct.setCreatedAt((Timestamp) object[5]);
            orderProduct.setUpdatedAt((Timestamp) object[6]);
            orderProducts.add(orderProduct);
        }
        return resultOrderProduct;
    }

    @GetMapping("/getOrderItemByOrderProductId/{orderProductId}")
    public List<BillDetail> getOrderItemByOrderProductId(@PathVariable int orderProductId) {
        List<Object[]> resultOrderItems = productService.getOrderItemByOrderProductId(orderProductId);
        List<BillDetail> billDetails = new ArrayList<>();
        for (Object[] object : resultOrderItems) {
            BillDetail billDetail = new BillDetail();
            billDetail.setId((int) object[0]);
            billDetail.setStatus((String) object[1]);
            billDetail.setProductId((int) object[2]);
            billDetail.setOrderProductId((int) object[3]);
            billDetail.setShopBoatId((int) object[4]);
            billDetail.setQuantity((Integer) object[5]);
            billDetail.setPrice((BigDecimal) object[6]);
            billDetail.setSale((BigDecimal) object[7]);
            billDetail.setProductName((String) object[8]);
            billDetail.setProductSlug((String) object[9]);
            billDetails.add(billDetail);
        }
        return billDetails;
    }

    // update address by id
    @PostMapping("/updateAddressById/{id}")
    public ResponseEntity<User> updateAddressById(
            @RequestParam String address,
            @PathVariable int id) {
        userService.updateAddressById(address, id);
        User user = userService.getUserById(id);
        User userResult = new User();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setAddress(user.getAddress());
        userResult.setAvatar(user.getAvatar());
        userResult.setPhoneNumber(user.getPhoneNumber());
        userResult.setUsername(user.getUsername());
        userResult.setRole(user.getRole());
        return ResponseEntity.ok(userResult);
    }

    // create new report
    @PostMapping("/createNewReport")
    public ResponseEntity<String> createNewReport(@RequestBody ReportShopboat reportShopboat) {
        reportShopboatService.createNewReport(reportShopboat.getShop_boat_id(), reportShopboat.getUser_id(),
                reportShopboat.getDescription(), reportShopboat.getImgrp(), reportShopboat.getCode_order_product(),
                reportShopboat.getStatus(),
                reportShopboat.getCreated_at());
        return ResponseEntity.ok("Report created successfully");
    }

}
