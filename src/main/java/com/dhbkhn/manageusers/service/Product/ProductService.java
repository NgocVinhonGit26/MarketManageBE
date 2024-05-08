package com.dhbkhn.manageusers.service.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;

import com.dhbkhn.manageusers.model.OrderItemSum;
import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;

public interface ProductService {
        public List<Product> getAllProduct();

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
        public Page<Product> searchProduct(String name, Double priceFrom, Double priceTo, Integer countInStock,
                        String category, Double sale, int page, int shopBoatId);

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale for
        // user
        public Page<Product> searchProductForUser(String name, Double priceFrom, Double priceTo, Integer countInStock,
                        String category, Double sale, int page);

        public List<Product> findAllByOrderByCategory();

        // get product by slug
        public Product findBySlug(String slug);

        // insert order product
        public void createOrderProduct(String status, String paymentMethod, BigDecimal total,
                        int customer, Timestamp createdAt, Timestamp updatedAt);

        // get last order product by id customer
        public int getLastOrderProduct(int customerId);

        // get order product by id shopboat and id order item
        public Page<Object[]> getAllListOrderProduct(int shopBoatId, int page);

        // ORDER ITEM---------------------------------------------

        // insert order item
        public void insertOrderItem(String status, int productId, int orderProductId, int shopBoatId, int quantity,
                        BigDecimal price,
                        BigDecimal sale);

        // get all order item
        public Page<Object[]> getAllListOrderItem(int shopBoatId, int page);

        // get order product by id
        public List<Object[]> getOrderProductById(int id);

        // update status order product by id
        public void updateStatusOrderItemById(String status, int id);

        // get order item by order product id
        public List<Object[]> getOrderItemByOrderProductId(int shopBoatId, int orderProductId);

        // get total order item by shop boat id in today
        public Object getTotalOrderItemByShopBoatId(int shopBoatId);

        // get total order item by order product id and shop boat id in 0h-3h,
        // 3h-6h,....
        public List<Object[]> getTotalOrderItemByShopBoatIdInTimeSlot(int shopBoatId);

        // get total order item by order product id and shop boat id in week
        public Object getTotalOrderItemByShopBoatIdInWeek(int shopBoatId);

        // get total order item by order product id and shop boat id in thứ 2, thứ 3,
        // thứ 4, thứ 5, thứ 6, thứ 7, chủ nhật
        public List<Object[]> getTotalOrderItemByShopBoatIdInDayOfWeek(int shopBoatId);

        // get total order item by order product id and shop boat id in month
        public Object getTotalOrderItemByShopBoatIdInMonth(int shopBoatId);

        // get total order item by order product id and shop boat id in tuần 1, tuần 2,
        // tuần 3, tuần 4, tuần 5
        public List<Object[]> getTotalOrderItemByShopBoatIdInWeekOfMonth(int shopBoatId);

        // get total order item by order product id and shop boat id in year
        public Object getTotalOrderItemByShopBoatIdInYear(int shopBoatId);

        // get total order item by order product id and shop boat id in tháng 1, tháng
        // 2,...
        public List<Object[]> getTotalOrderItemByShopBoatIdInMonthOfYear(int shopBoatId);

}
