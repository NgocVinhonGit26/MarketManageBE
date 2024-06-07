package com.dhbkhn.manageusers.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.dhbkhn.manageusers.DTO.CommentsDTO;
import com.dhbkhn.manageusers.model.Product.OrderItem;
import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.service.Comments.CommentsService;
import com.dhbkhn.manageusers.service.Product.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CommentsService commentsService;

    @Autowired
    public ProductController(ProductService productService, CommentsService commentsService) {
        this.productService = productService;
        this.commentsService = commentsService;
    }

    // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale for
    // user
    @GetMapping("/searchProductForUser/{page}")
    public Page<Product> searchProductForUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Integer countInStock,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double sale,
            @PathVariable int page) {
        return productService.searchProductForUser(name, priceFrom, priceTo, countInStock, category, sale, page);
    }

    // get all products
    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
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

    // update total order product by id
    @PostMapping("/updateTotalOrderProductById")
    public ResponseEntity<String> updateTotalOrderProductById(@RequestBody OrderProduct orderProduct) {
        productService.updateTotalOrderProductById(orderProduct.getTotal(), orderProduct.getId());
        return ResponseEntity.ok("Total order product updated successfully!");
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

    @GetMapping("/searchProductByName/{page}")
    public List<Product> searchProductByName(
            @RequestParam(name = "name", required = false) String name,
            @PathVariable int page) {
        return productService.searchProductByName(name, page).getContent();
    }

    // get total
    @GetMapping("/getTotalPageSearchProductByName/{page}")
    public int getTotalPageSearchProductByName(
            @RequestParam(name = "name", required = false) String name,
            @PathVariable int page) {
        return productService.searchProductByName(name, page).getTotalPages();
    }

    // get all comments by product id
    @GetMapping("/findAllByProductId/{productId}")
    public List<CommentsDTO> findAllByProductId(
            @PathVariable int productId) {
        List<Object[]> resultComments = commentsService.findAllByProductId(productId);
        List<CommentsDTO> comments = new ArrayList<>();
        for (Object[] result : resultComments) {
            int id = ((Number) result[0]).intValue();
            int productIdResult = ((Number) result[1]).intValue();
            int userId = ((Number) result[2]).intValue();
            String content = (String) result[3];
            Timestamp timestamp = (Timestamp) result[4];
            String userName = (String) result[5];
            String userAvatar = (String) result[6];
            int likes = ((Number) result[7]).intValue();
            int dislikes = ((Number) result[8]).intValue();

            // Convert Timestamp to LocalDateTime
            LocalDateTime localDateTime = timestamp.toLocalDateTime();

            // Format LocalDateTime to 'yyyy-MM-dd HH:mm:ss' string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String createdAt = localDateTime.format(formatter);

            CommentsDTO comment = new CommentsDTO(id, productIdResult, userId, content, createdAt,
                    likes, dislikes, userName, userAvatar);
            comments.add(comment);
        }
        return comments;

    }

}
