package com.dhbkhn.manageusers.repository;

import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.Product.Product;

public interface ProductRepsitory extends JpaRepository<Product, Integer> {

        // create a new product
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO product (name, slug, description, price, sale, count_in_stock, image, unit, category, shop_boat_id, created_at, updated_at, video_infor) "
                        +
                        "VALUES (:name, :slug, :description, :price, :sale, :countInStock, :image, :unit, :category, :shopBoatId, :createdAt, :updatedAt, :videoInfor)", nativeQuery = true)
        void createNewProduct(@Param("name") String name, @Param("slug") String slug,
                        @Param("description") String description,
                        @Param("price") BigDecimal price, @Param("sale") BigDecimal sale,
                        @Param("countInStock") int countInStock,
                        @Param("image") String image, @Param("unit") String unit, @Param("category") String category,
                        @Param("shopBoatId") int shopBoatId, @Param("createdAt") Timestamp createdAt,
                        @Param("updatedAt") Timestamp updatedAt,
                        @Param("videoInfor") String videoInfor);

        // get all product

        @Query(value = "SELECT p.*, sb.name AS shop_name " +
                        "FROM product p " +
                        "JOIN shopboat sb ON p.shop_boat_id = sb.id " +
                        "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
                        "AND (:priceFrom IS NULL OR p.price >= :priceFrom) " +
                        "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
                        "AND (:countInStock IS NULL OR p.count_in_stock > 0) " +
                        "AND (:category IS NULL OR p.category = :category) " +
                        "AND (:sale IS NULL OR p.sale = :sale)", nativeQuery = true)
        Page<Object[]> getAllProductAdmin(Pageable pageable,
                        @Param("name") String name,
                        @Param("priceFrom") BigDecimal priceFrom,
                        @Param("priceTo") BigDecimal priceTo,
                        @Param("countInStock") Boolean countInStock,
                        @Param("category") String category,
                        @Param("sale") BigDecimal sale);

        // update a product by id
        @Transactional
        @Modifying
        @Query(value = "UPDATE product SET name = :name, slug = :slug, description = :description, price = :price, sale = :sale, count_in_stock = :countInStock, image = :image, unit = :unit, "
                        + "category = :category, updated_at = :updatedAt, video_infor = :videoInfor WHERE id = :id", nativeQuery = true)
        void updateProductById(@Param("name") String name, @Param("slug") String slug,
                        @Param("description") String description,
                        @Param("price") BigDecimal price, @Param("sale") BigDecimal sale,
                        @Param("countInStock") int countInStock,
                        @Param("image") String image, @Param("unit") String unit, @Param("category") String category,
                        @Param("updatedAt") Timestamp updatedAt,
                        @Param("videoInfor") String videoInfor,
                        @Param("id") int id);

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
        @Query(value = "SELECT * FROM product "
                        + "WHERE shop_boat_id = :shopBoatId "
                        + "AND (:name IS NULL OR name LIKE %:name%) "
                        + "AND (:priceFrom IS NULL OR price >= :priceFrom) "
                        + "AND (:priceTo IS NULL OR price <= :priceTo) "
                        + "AND (:countInStock IS NULL OR count_in_stock > 0) "
                        + "AND (:category IS NULL OR category = :category) "
                        + "AND (:sale IS NULL OR sale >= :sale)", nativeQuery = true)
        Page<Product> searchProduct(
                        @Param("name") String name,
                        @Param("priceFrom") Double priceFrom,
                        @Param("priceTo") Double priceTo,
                        @Param("countInStock") Boolean countInStock,
                        @Param("category") String category,
                        @Param("sale") Double sale,
                        Pageable pageable,
                        @Param("shopBoatId") int shopBoatId);

        // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale for
        // user
        @Query(value = "SELECT * FROM product "
                        + "WHERE "
                        + "(:name IS NULL OR name LIKE %:name%) "
                        + "AND (:priceFrom IS NULL OR price >= :priceFrom) "
                        + "AND (:priceTo IS NULL OR price <= :priceTo) "
                        + "AND (:countInStock IS NULL OR count_in_stock = :countInStock) "
                        + "AND (:category IS NULL OR category = :category) "
                        + "AND (:sale IS NULL OR sale = :sale)", nativeQuery = true)
        Page<Product> searchProductForUser(
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

        // get order product by id shopboat and id order item
        @Query(value = "SELECT " +
                        "order_product.*, " +
                        "user.name AS customer_name, " +
                        "user.phone_number AS customer_phone_number, " +
                        "user.address AS customer_address, " +
                        "order_item.status AS orderitem_status " +
                        "FROM order_product " +
                        "INNER JOIN order_item ON order_product.id = order_item.order_product_id " +
                        "INNER JOIN user ON order_product.customer = user.id " +
                        "WHERE order_item.shop_boat_id = :shopBoatId " +
                        "GROUP BY order_product.id, order_item.status", nativeQuery = true)
        Page<Object[]> getAllListOrderProduct(@Param("shopBoatId") int shopBoatId, Pageable pageable);

        // update total order product by id
        @Modifying
        @Query(value = "UPDATE order_product SET total = :total WHERE id = :id", nativeQuery = true)
        void updateTotalOrderProductById(@Param("total") BigDecimal total, @Param("id") int id);

        // update quantity product by id
        @Modifying
        @Query(value = "UPDATE Product SET count_in_stock = count_in_stock - :orderQuantity WHERE id = :id", nativeQuery = true)
        void updateQuantityProductById(@Param("orderQuantity") int countInStock, @Param("id") int id);

        // ORDER PRODUCT---------------------------------------------
        // insert order product

        @Modifying
        @Query(value = "INSERT INTO order_product (status, payment_method, total, customer, created_at, updated_at) "
                        +
                        "VALUES (:status, :paymentMethod, :total, :customer, :createdAt, :updatedAt)", nativeQuery = true)
        void createOrderProduct(
                        @Param("status") String status,
                        @Param("paymentMethod") String paymentMethod,
                        @Param("total") BigDecimal total,
                        @Param("customer") int customer,
                        @Param("createdAt") Timestamp createdAt,
                        @Param("updatedAt") Timestamp updatedAt);

        // get last order product by id customer
        @Query(value = "SELECT id FROM order_product WHERE customer = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
        int getLastOrderProduct(@Param("userId") int userId);

        // insert order item
        @Modifying
        @Query(value = "INSERT INTO order_item (status, product_id, order_product_id, shop_boat_id, quantity, price, sale) VALUES (:status, :productId, :orderProductId, :shopBoatId, :quantity, :price, :sale)", nativeQuery = true)
        void insertOrderItem(
                        @Param("status") String status,
                        @Param("productId") int productId,
                        @Param("orderProductId") int orderProductId,
                        @Param("shopBoatId") int shopBoatId,
                        @Param("quantity") int quantity,
                        @Param("price") BigDecimal price,
                        @Param("sale") BigDecimal sale);

        // get all order item by shop_boat_id
        @Query(value = "SELECT oi.*, u.name, u.phone_number, u.address " +
                        "FROM order_item AS oi " +
                        "JOIN order_product AS op ON oi.order_product_id = op.id " +
                        "JOIN user AS u ON op.customer = u.id " +
                        "WHERE oi.shop_boat_id = :shopBoatId", nativeQuery = true)
        Page<Object[]> getAllListOrderItem(
                        @Param("shopBoatId") int shopBoatId,
                        Pageable pageable);

        // get order item by id shop
        @Query(value = "SELECT ot.*, u.name, u.phone_number, u.address " +
                        "FROM item AS ot " +
                        "JOIN user AS u ON ot.customer = u.id " +
                        "WHERE op.id = :orderId", nativeQuery = true)
        List<Object[]> getOrderProductById(@Param("orderId") int orderId);

        // update status order product by id
        @Modifying
        @Query(value = "UPDATE order_item SET status = :status WHERE order_product_id = :orderProductId AND shop_boat_id = :shopBoatId", nativeQuery = true)
        void updateStatusOrderItemById(
                        @Param("status") String status,
                        @Param("orderProductId") int orderProductId,
                        @Param("shopBoatId") int shopBoatId);

        // get order item by order product id
        @Query(value = "SELECT oi.*, p.name, p.image FROM order_item AS oi " +
                        "JOIN product AS p ON oi.product_id = p.id " +
                        "WHERE oi.order_product_id = :orderProductId AND oi.shop_boat_id = :shopBoatId", nativeQuery = true)
        List<Object[]> getOrderItemByOrderProductId(
                        @Param("shopBoatId") int shopBoatId,
                        @Param("orderProductId") int orderProductId);

        // get order product by customer
        @Query(value = "SELECT * FROM order_product WHERE customer = :customerId", nativeQuery = true)
        List<Object[]> getOrderProductByCustomer(@Param("customerId") int customerId);

        // get order item by order product id
        @Query(value = "SELECT oi.*, p.name, p.slug FROM order_item oi JOIN product p ON oi.product_id = p.id " +
                        "WHERE oi.order_product_id = :orderProductId", nativeQuery = true)
        List<Object[]> getOrderItemByOrderProductId(@Param("orderProductId") int orderProductId);

        // search order product b
        @Query(value = "SELECT " +
                        "order_product.*, " +
                        "user.name AS customer_name, " +
                        "user.phone_number AS customer_phone_number, " +
                        "user.address AS customer_address, " +
                        "order_item.status AS orderitem_status " +
                        "FROM order_product " +
                        "INNER JOIN order_item ON order_product.id = order_item.order_product_id " +
                        "INNER JOIN user ON order_product.customer = user.id " +
                        "WHERE order_item.shop_boat_id = :shopBoatId AND " +
                        "(:customerName IS NULL OR user.name LIKE %:customerName%) AND " +
                        "(:customerPhoneNumber IS NULL OR user.phone_number LIKE %:customerPhoneNumber%) AND " +
                        "(:customerAddress IS NULL OR user.address LIKE %:customerAddress%) AND " +
                        "(:dateFrom IS NULL OR DATE(order_product.created_at) >= :dateFrom) AND " +
                        "(:dateTo IS NULL OR DATE(order_product.created_at) <= :dateTo) AND " +
                        "(:priceFrom IS NULL OR order_product.total >= :priceFrom) AND " +
                        "(:priceTo IS NULL OR order_product.total <= :priceTo) AND " +
                        "(:status IS NULL OR order_item.status = :status) " +
                        "GROUP BY order_product.id, order_item.status", nativeQuery = true)
        Page<Object[]> searchOrderProduct(
                        @Param("customerName") String customerName,
                        @Param("customerPhoneNumber") String customerPhoneNumber,
                        @Param("customerAddress") String customerAddress,
                        @Param("dateFrom") Timestamp dateFrom,
                        @Param("dateTo") Timestamp dateTo,
                        @Param("priceFrom") BigDecimal priceFrom,
                        @Param("priceTo") BigDecimal priceTo,
                        @Param("status") String status,
                        @Param("shopBoatId") int shopBoatId,
                        Pageable pageable);

        // ------THONG KE DOANH THU SHOP BOAT---------------------
        // get total order item by order product id and shop boat id in today
        @Query(value = "SELECT COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount " +
                        "FROM order_item oi " +
                        "JOIN order_product op ON oi.order_product_id = op.id " +
                        "WHERE oi.shop_boat_id = :shopBoatId AND DATE(op.created_at) = CURDATE() AND oi.status= 'completed'", nativeQuery = true)
        Object getTotalOrderItemByShopBoatId(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in 0h-3h,
        // 3h-6h,....
        @Query(value = "SELECT "
                        + "CONCAT( "
                        + "CASE "
                        + "WHEN HOUR(op.created_at) BETWEEN 0 AND 2 THEN '0h-3h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 3 AND 5 THEN '3h-6h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 6 AND 8 THEN '6h-9h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 9 AND 11 THEN '9h-12h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 12 AND 14 THEN '12h-15h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 15 AND 17 THEN '15h-18h' "
                        + "WHEN HOUR(op.created_at) BETWEEN 18 AND 20 THEN '18h-21h' "
                        + "ElSE '21h-24h' "
                        + "END "
                        + ") AS time_slot, "
                        + "COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount "
                        + "FROM order_item oi "
                        + "JOIN order_product op ON oi.order_product_id = op.id "
                        + "WHERE HOUR(op.created_at) BETWEEN 0 AND 23 "
                        + "AND DATE(op.created_at) = CURDATE() "
                        + "AND oi.shop_boat_id = :shopBoatId "
                        + "AND oi.status= 'completed' "
                        + "GROUP BY time_slot "
                        + "ORDER BY time_slot", nativeQuery = true)
        List<Object[]> getTotalOrderItemByShopBoatIdInTimeSlot(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in week
        @Query(value = "SELECT COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount " +
                        "FROM order_item oi " +
                        "JOIN order_product op ON oi.order_product_id = op.id " +
                        "WHERE oi.shop_boat_id = :shopBoatId AND WEEK(op.created_at) = WEEK(CURDATE()) AND oi.status= 'completed'", nativeQuery = true)
        Object getTotalOrderItemByShopBoatIdInWeek(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in thứ 2, thứ 3,
        // thứ 4, thứ 5, thứ 6, thứ 7, chủ nhật
        @Query(value = "SELECT "
                        + "CASE DAYOFWEEK(op.created_at) "
                        + "WHEN 2 THEN 'Thứ 2' "
                        + "WHEN 3 THEN 'Thứ 3' "
                        + "WHEN 4 THEN 'Thứ 4' "
                        + "WHEN 5 THEN 'Thứ 5' "
                        + "WHEN 6 THEN 'Thứ 6' "
                        + "WHEN 7 THEN 'Thứ 7' "
                        + "ELSE 'Chủ nhật' "
                        + "END AS day_of_week, "
                        + "COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount "
                        + "FROM order_item oi "
                        + "INNER JOIN order_product op ON oi.order_product_id = op.id "
                        + "WHERE  oi.shop_boat_id = :shopBoatId AND WEEK(op.created_at) = WEEK(CURDATE()) AND oi.status= 'completed'"
                        + "GROUP BY day_of_week "
                        + "ORDER BY day_of_week", nativeQuery = true)
        List<Object[]> getTotalOrderItemByShopBoatIdInDayOfWeek(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in month
        @Query(value = "SELECT COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount " +
                        "FROM order_item oi " +
                        "JOIN order_product op ON oi.order_product_id = op.id " +
                        "WHERE oi.shop_boat_id = :shopBoatId AND MONTH(op.created_at) = MONTH(CURDATE()) AND YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status= 'completed'", nativeQuery = true)
        Object getTotalOrderItemByShopBoatIdInMonth(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in tuần 1, tuần 2,
        // tuần 3, tuần 4, tuần 5
        @Query(value = "SELECT "
                        + "CONCAT('Tuần ', WEEK(op.created_at) - WEEK(DATE_SUB(op.created_at, INTERVAL DAYOFMONTH(op.created_at) - 1 DAY)) + 1) AS week_of_month, "
                        + "COUNT(oi.id) AS total_orders, "
                        + "SUM(oi.price) AS total_revenue "
                        + "FROM order_item oi "
                        + "INNER JOIN order_product op ON oi.order_product_id = op.id "
                        + "WHERE oi.shop_boat_id = :shopBoatId AND MONTH(op.created_at) = MONTH(CURDATE()) AND YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status= 'completed'"
                        + "GROUP BY week_of_month "
                        + "ORDER BY week_of_month", nativeQuery = true)
        List<Object[]> getTotalOrderItemByShopBoatIdInWeekOfMonth(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in year
        @Query(value = "SELECT COUNT(oi.id) AS total_orders, SUM(oi.price) AS total_amount " +
                        "FROM order_item oi " +
                        "JOIN order_product op ON oi.order_product_id = op.id " +
                        "WHERE oi.shop_boat_id = :shopBoatId AND YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status= 'completed'", nativeQuery = true)
        Object getTotalOrderItemByShopBoatIdInYear(@Param("shopBoatId") int shopBoatId);

        // get total order item by order product id and shop boat id in tháng 1, tháng
        // 2,...
        @Query(value = "SELECT "
                        + "CONCAT('T', MONTH(op.created_at)) AS month, "
                        + "COUNT(oi.id) AS total_orders, "
                        + "SUM(oi.price) AS total_revenue "
                        + "FROM order_item oi "
                        + "INNER JOIN order_product op ON oi.order_product_id = op.id "
                        + "WHERE oi.shop_boat_id = :shopBoatId AND YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status= 'completed'"
                        + "GROUP BY month "
                        + "ORDER BY month", nativeQuery = true)
        List<Object[]> getTotalOrderItemByShopBoatIdInMonthOfYear(@Param("shopBoatId") int shopBoatId);

        // get top 5 product by revenue in today
        @Query(value = "SELECT " +
                        "ROW_NUMBER() OVER (ORDER BY SUM(order_item.price) DESC) AS STT, " +
                        "product.name, " +
                        "product.image, " +
                        "SUM(order_item.quantity) AS total_quantity, " +
                        "SUM(order_item.price) AS total_revenue " +
                        "FROM " +
                        "order_item " +
                        "INNER JOIN " +
                        "product ON order_item.product_id = product.id " +
                        "INNER JOIN " +
                        "order_product ON order_item.order_product_id = order_product.id " +
                        "WHERE " +
                        "DATE(order_product.created_at) = CURDATE() " +
                        "AND order_item.status = 'completed' " +
                        "AND order_item.shop_boat_id = :shopBoatId " +
                        "GROUP BY " +
                        "product.id " +
                        "ORDER BY " +
                        "total_revenue DESC " +
                        "LIMIT 5", nativeQuery = true)
        List<Object[]> getTop5ProductByRevenueInToday(
                        @Param("shopBoatId") int shopBoatId);

        // get total price of order item by id shop boat in this week
        @Query(value = "SELECT " +
                        "ROW_NUMBER() OVER (ORDER BY SUM(order_item.price) DESC) AS STT, " +
                        "product.name, " +
                        "product.image, " +
                        "SUM(order_item.quantity) AS total_quantity, " +
                        "SUM(order_item.price) AS total_revenue " +
                        "FROM " +
                        "order_item " +
                        "INNER JOIN " +
                        "product ON order_item.product_id = product.id " +
                        "INNER JOIN " +
                        "order_product ON order_item.order_product_id = order_product.id " +
                        "WHERE " +
                        "YEARWEEK(order_product.created_at) = YEARWEEK(CURDATE()) " +
                        "AND order_item.status = 'completed' " +
                        "AND order_item.shop_boat_id = :shopBoatId " +
                        "GROUP BY " +
                        "product.id " +
                        "ORDER BY " +
                        "total_revenue DESC " +
                        "LIMIT 5", nativeQuery = true)
        List<Object[]> getTop5ProductByRevenueInThisWeek(
                        @Param("shopBoatId") int shopBoatId);

        // get total price of order item by id shop boat in this month
        @Query(value = "SELECT " +
                        "ROW_NUMBER() OVER (ORDER BY SUM(order_item.price) DESC) AS STT, " +
                        "product.name, " +
                        "product.image, " +
                        "SUM(order_item.quantity) AS total_quantity, " +
                        "SUM(order_item.price) AS total_revenue " +
                        "FROM " +
                        "order_item " +
                        "INNER JOIN " +
                        "product ON order_item.product_id = product.id " +
                        "INNER JOIN " +
                        "order_product ON order_item.order_product_id = order_product.id " +
                        "WHERE " +
                        "MONTH(order_product.created_at) = MONTH(CURDATE()) " +
                        "AND YEAR(order_product.created_at) = YEAR(CURDATE()) " +
                        "AND order_item.status = 'completed' " +
                        "AND order_item.shop_boat_id = :shopBoatId " +
                        "GROUP BY " +
                        "product.id " +
                        "ORDER BY " +
                        "total_revenue DESC " +
                        "LIMIT 5", nativeQuery = true)
        List<Object[]> getTop5ProductByRevenueInThisMonth(
                        @Param("shopBoatId") int shopBoatId);

        // get total price of order item by id shop boat in this year
        @Query(value = "SELECT " +
                        "ROW_NUMBER() OVER (ORDER BY SUM(order_item.price) DESC) AS STT, " +
                        "product.name, " +
                        "product.image, " +
                        "SUM(order_item.quantity) AS total_quantity, " +
                        "SUM(order_item.price) AS total_revenue " +
                        "FROM " +
                        "order_item " +
                        "INNER JOIN " +
                        "product ON order_item.product_id = product.id " +
                        "INNER JOIN " +
                        "order_product ON order_item.order_product_id = order_product.id " +
                        "WHERE " +
                        "YEAR(order_product.created_at) = YEAR(CURDATE()) " +
                        "AND order_item.status = 'completed' " +
                        "AND order_item.shop_boat_id = :shopBoatId " +
                        "GROUP BY " +
                        "product.id " +
                        "ORDER BY " +
                        "total_revenue DESC " +
                        "LIMIT 5", nativeQuery = true)
        List<Object[]> getTop5ProductByRevenueInThisYear(
                        @Param("shopBoatId") int shopBoatId);

        // search product by name
        @Query(value = "SELECT * FROM product WHERE :name IS NULL OR name LIKE %:name%", nativeQuery = true)

        Page<Product> searchProductByName(@Param("name") String name, Pageable pageable);

        // THONG KE DOANH THU SHOP BOAT--------------------- CASE của ADMIN
        // get total price of order item by id shop boat in this month
        @Query(value = "SELECT sb.id AS shop_boat_id, sb.name AS shop_name, SUM(oi.price) AS total_revenue " +
                        "FROM order_item oi " +
                        "INNER JOIN order_product op ON oi.order_product_id = op.id " +
                        "INNER JOIN shopboat sb ON oi.shop_boat_id = sb.id " +
                        "WHERE MONTH(op.created_at) = MONTH(CURDATE()) AND YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status = 'completed' "
                        +
                        "GROUP BY sb.id, sb.name " +
                        "ORDER BY sb.id", nativeQuery = true)
        List<Object[]> getTotalPriceOrderItemByShopBoatIdInMonth();

        // get total price of order item by id shop boat in this year
        @Query(value = "SELECT sb.id AS shop_boat_id, sb.name AS shop_name, SUM(oi.price) AS total_revenue " +
                        "FROM order_item oi " +
                        "INNER JOIN order_product op ON oi.order_product_id = op.id " +
                        "INNER JOIN shopboat sb ON oi.shop_boat_id = sb.id " +
                        "WHERE YEAR(op.created_at) = YEAR(CURDATE()) AND oi.status = 'completed' " +
                        "GROUP BY sb.id, sb.name " +
                        "ORDER BY sb.id", nativeQuery = true)
        List<Object[]> getTotalPriceOrderItemByShopBoatIdInYear();
}
