package com.dhbkhn.manageusers.service.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;

import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;

public interface ProductService {
        public List<Product> getAllProduct();

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
        public Page<Product> searchProduct(String name, Double priceFrom, Double priceTo, Integer countInStock,
                        String category, Double sale, int page);

        public List<Product> findAllByOrderByCategory();

        // get product by slug
        public Product findBySlug(String slug);

        // insert order product
        public void createOrderProduct(String status, String paymentMethod, BigDecimal total, int shopBoatId,
                        int customer, Timestamp createdAt, Timestamp updatedAt);

        // get last order product by id customer
        public int getLastOrderProduct(int customerId);

        // insert order item
        public void insertOrderItem(int productId, int orderProductId, int quantity, BigDecimal price, BigDecimal sale);

        // get all order product
        public Page<Object[]> getAllListOrderProduct(int shopBoatId, int page);

        // get order product by id
        public List<Object[]> getOrderProductById(int id);

        // update status order product by id
        public void updateStatusOrderProductById(String status, int id);

        // get order item by order product id
        public List<Object[]> getOrderItemByOrderProductId(int orderProductId);
}
