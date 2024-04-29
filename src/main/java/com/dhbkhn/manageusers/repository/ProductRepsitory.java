package com.dhbkhn.manageusers.repository;

import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;

public interface ProductRepsitory extends JpaRepository<Product, Integer> {

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
        @Query(value = "SELECT p FROM Product p "
                        + "WHERE "
                        + "(:name IS NULL OR p.name LIKE %:name%) "
                        + "AND (:priceFrom IS NULL OR p.price >= :priceFrom) "
                        + "AND (:priceTo IS NULL OR p.price <= :priceTo) "
                        + "AND (:countInStock IS NULL OR p.countInStock = :countInStock) "
                        + "AND (:category IS NULL OR p.category = :category) "
                        + "AND (:sale IS NULL OR p.sale = :sale)")
        Page<Product> searchProduct(
                        @Param("name") String name,
                        @Param("priceFrom") Double priceFrom,
                        @Param("priceTo") Double priceTo,
                        @Param("countInStock") Integer countInStock,
                        @Param("category") String category,
                        @Param("sale") Double sale,
                        Pageable pageable);

        List<Product> findAllByOrderByCategory();

        // get product by slug
        Product findBySlug(String slug);

        // ORDER PRODUCT---------------------------------------------
        // insert order product

        @Modifying
        @Query(value = "INSERT INTO order_product (status, payment_method, total, shop_boat_id, customer, created_at, updated_at) "
                        +
                        "VALUES (:status, :paymentMethod, :total, :shopBoatId, :customer, :createdAt, :updatedAt)", nativeQuery = true)
        void createOrderProduct(
                        @Param("status") String status,
                        @Param("paymentMethod") String paymentMethod,
                        @Param("total") BigDecimal total,
                        @Param("shopBoatId") int shopBoatId,
                        @Param("customer") int customer,
                        @Param("createdAt") Timestamp createdAt,
                        @Param("updatedAt") Timestamp updatedAt);

        // get last order product by id customer
        @Query(value = "SELECT id FROM order_product WHERE customer = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
        int getLastOrderProduct(@Param("userId") int userId);

        // insert order item
        @Modifying
        @Query(value = "INSERT INTO order_item (product_id, order_product_id, quantity, price, sale) VALUES (:productId, :orderProductId, :quantity, :price, :sale)", nativeQuery = true)
        void insertOrderItem(@Param("productId") int productId,
                        @Param("orderProductId") int orderProductId,
                        @Param("quantity") int quantity,
                        @Param("price") BigDecimal price,
                        @Param("sale") BigDecimal sale);

        // get all order product by shop_boat_id
        @Query(value = "SELECT op.* , u.name, u.phone_number, u.address " +
                        "FROM order_product AS op " +
                        "JOIN User AS u ON op.customer = u.id " +
                        "WHERE shop_boat_id = :shopBoatId", nativeQuery = true)
        Page<Object[]> getAllListOrderProduct(
                        @Param("shopBoatId") int shopBoatId,
                        Pageable pageable);

        // get order product by id
        @Query(value = "SELECT op.*, u.name, u.phone_number, u.address " +
                        "FROM order_product AS op " +
                        "JOIN User AS u ON op.customer = u.id " +
                        "WHERE op.id = :orderId", nativeQuery = true)
        List<Object[]> getOrderProductById(@Param("orderId") int orderId);

        // update status order product by id
        @Modifying
        @Query(value = "UPDATE order_product SET status = :status WHERE id = :id", nativeQuery = true)
        void updateStatusOrderProductById(@Param("status") String status, @Param("id") int id);

        // get order item by order product id
        @Query(value = "SELECT oi.*, p.name, p.image FROM order_item AS oi " +
                        "JOIN product AS p ON oi.product_id = p.id " +
                        "WHERE oi.order_product_id = :orderProductId", nativeQuery = true)
        List<Object[]> getOrderItemByOrderProductId(@Param("orderProductId") int orderProductId);
}
