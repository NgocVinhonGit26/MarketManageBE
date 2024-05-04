package com.dhbkhn.manageusers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.dhbkhn.manageusers.model.Product.OrderItem;
import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.service.Product.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // get all products
    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
    @GetMapping("/searchProduct/{page}")
    public List<Product> searchProduct(
            @PathVariable int page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Integer countInStock,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double sale) {
        Page<Product> pageResult = productService.searchProduct(name, priceFrom, priceTo, countInStock, category, sale,
                page);
        return pageResult.getContent();
    }

    // get total page
    @GetMapping("/getTotalPage/{page}")
    public int getTotalPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Integer countInStock,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double sale,
            @PathVariable int page) {
        Page<Product> pageResult = productService.searchProduct(name, priceFrom, priceTo, countInStock, category,
                sale, page);
        return pageResult.getTotalPages();
    }

    // get all products order by category
    @GetMapping("/getAllProductOrderByCategory")
    public Map<String, List<Product>> getAllProductOrderByCategory() {
        List<Product> listProduct = productService.findAllByOrderByCategory();
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        for (Product product : listProduct) {
            String category = product.getCategory();
            if (!productsByCategory.containsKey(category)) {
                productsByCategory.put(category, new ArrayList<>());
            }
            productsByCategory.get(category).add(product);
        }
        return productsByCategory;
    }

    // get product by slug
    @GetMapping("/getProductBySlug/{slug}")
    public Product getProductBySlug(@PathVariable String slug) {
        return productService.findBySlug(slug);
    }

    // ORDER ITEM---------------------------------------------

    // insert order product
    @PostMapping("/createOrderProduct")
    public ResponseEntity<String> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        productService.createOrderProduct(orderProduct.getStatus(), orderProduct.getPaymentMethod(),
                orderProduct.getTotal(), orderProduct.getCustomer(),
                orderProduct.getCreatedAt(), orderProduct.getUpdatedAt());
        return ResponseEntity.ok("Order product created successfully!");

    }

    // get last order product by id customer
    @GetMapping("/getLastOrderProduct")
    public int getLastOrderProduct(@RequestParam int userId) {
        return productService.getLastOrderProduct(userId);
    }

    // insert order item
    @PostMapping("/insertOrderItem")
    public ResponseEntity<String> insertOrderItem(@RequestBody OrderItem orderItem) {
        productService.insertOrderItem(orderItem.getStatus(), orderItem.getProductId(), orderItem.getOrderProductId(),
                orderItem.getShopBoatId(), orderItem.getQuantity(), orderItem.getPrice(), orderItem.getSale());
        return ResponseEntity.ok("Order item inserted successfully!");
    }

}
