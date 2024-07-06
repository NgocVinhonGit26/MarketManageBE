package com.dhbkhn.manageusers.service.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.repository.ProductRepsitory;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepsitory productRepsitory;

    // get all product admin
    @Override
    public Page<Object[]> getAllProductAdmin(String name, BigDecimal priceFrom, BigDecimal priceTo,
            Boolean countInStock, String category, BigDecimal sale, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Object[]> pageResult = productRepsitory.getAllProductAdmin(pageable, name, priceFrom, priceTo,
                    countInStock, category, sale);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    // create new product
    @Override
    @Transactional
    public void createNewProduct(String name, String slug, String description, BigDecimal price, BigDecimal sale,
            int countInStock, String image, String unit, String category, int shopBoatId, Timestamp createdAt,
            Timestamp updatedAt, String videoInfor) {
        productRepsitory.createNewProduct(name, slug, description, price, sale, countInStock, image, unit, category,
                shopBoatId, createdAt, updatedAt, videoInfor);
    }

    // update a product by id
    @Override
    @Transactional
    public void updateProductById(String name, String slug, String description, BigDecimal price, BigDecimal sale,
            int countInStock, String image, String unit, String category, Timestamp updatedAt,
            String videoInfor, int id) {
        productRepsitory.updateProductById(name, slug, description, price, sale, countInStock, image, unit, category,
                updatedAt, videoInfor, id);
    }

    @Autowired
    public ProductServiceImpl(ProductRepsitory productRepsitory) {
        this.productRepsitory = productRepsitory;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepsitory.findAll();
    }

    // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
    @Override
    public Page<Product> searchProduct(String name, Double priceFrom, Double priceTo, Boolean countInStock,
            String category, Double sale, int page, int shopBoatId) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Product> pageResult = productRepsitory.searchProduct(name, priceFrom, priceTo, countInStock, category,
                    sale, pageable, shopBoatId);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }

    }

    // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale for
    // user
    @Override
    public Page<Product> searchProductForUser(String name, Double priceFrom, Double priceTo, Integer countInStock,
            String category, Double sale, int page) {
        int pageSize = 8;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Product> pageResult = productRepsitory.searchProductForUser(name, priceFrom, priceTo, countInStock,
                    category, sale, pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public List<Product> findAllByOrderByCategory() {
        return productRepsitory.findAllByOrderByCategory();
    }

    // get product by slug
    @Override
    public Product findBySlug(String slug) {
        return productRepsitory.findBySlug(slug);
    }

    // update quantity product by id
    @Override
    @Transactional
    public void updateQuantityProductById(int orderQuantity, int productId) {
        productRepsitory.updateQuantityProductById(orderQuantity, productId);
    }

    // get product by id
    @Override
    public Product getProductById(int id) {
        try {
            return productRepsitory.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    // insert order product
    @Transactional
    @Override
    public void createOrderProduct(String status, String paymentMethod, BigDecimal total, int customer,
            Timestamp createdAt, Timestamp updatedAt) {
        productRepsitory.createOrderProduct(status, paymentMethod, total, customer, createdAt, updatedAt);
    }

    // get last order product by id customer
    @Override
    public int getLastOrderProduct(int userId) {
        return productRepsitory.getLastOrderProduct(userId);
    }

    // get order product by id shopboat and id order item
    @Override
    public Page<Object[]> getAllListOrderProduct(int shopBoatId, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Object[]> pageResult = productRepsitory.getAllListOrderProduct(shopBoatId, pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    // update total order product by id
    @Override
    @Transactional
    public void updateTotalOrderProductById(BigDecimal total, int orderProductId) {
        productRepsitory.updateTotalOrderProductById(total, orderProductId);
    }

    // ORDER ITEM---------------------------------------------

    // insert order item
    @Transactional
    @Override
    public void insertOrderItem(String status, int productId, int orderProductId, int shopBoatId, int quantity,
            BigDecimal price,
            BigDecimal sale) {
        productRepsitory.insertOrderItem(status, productId, orderProductId, shopBoatId, quantity, price, sale);
    }

    // search order product b
    @Override
    public Page<Object[]> searchOrderProduct(String customerName, String customerPhoneNumber,
            String customerAddress, Timestamp dateFrom, Timestamp dateTo,
            BigDecimal totalFrom, BigDecimal totalTo, String status, int shopBoatId, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Object[]> pageResult = productRepsitory.searchOrderProduct(customerName, customerPhoneNumber,
                    customerAddress, dateFrom, dateTo, totalFrom, totalTo, status, shopBoatId, pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    // get all order product
    @Override
    public Page<Object[]> getAllListOrderItem(int shopBoatId, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Object[]> pageResult = productRepsitory.getAllListOrderItem(shopBoatId, pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    // get order product by id
    @Override
    public List<Object[]> getOrderProductById(int id) {
        return productRepsitory.getOrderProductById(id);
    }

    // update status order product by id
    @Override
    @Transactional
    public void updateStatusOrderItemById(String status, int orderProductId, int shopBoatId) {
        productRepsitory.updateStatusOrderItemById(status, orderProductId, shopBoatId);
    }

    // get order item by order product id
    @Override
    public List<Object[]> getOrderItemByOrderProductId(int shopBoatId, int orderProductId) {
        return productRepsitory.getOrderItemByOrderProductId(shopBoatId, orderProductId);
    }

    // get order product by customer
    @Override
    public List<Object[]> getOrderProductByCustomer(int customerId) {
        return productRepsitory.getOrderProductByCustomer(customerId);
    }

    // get order item by order product id
    @Override
    public List<Object[]> getOrderItemByOrderProductId(int orderProductId) {
        return productRepsitory.getOrderItemByOrderProductId(orderProductId);
    }

    // ------THONG KE DOANH THU SHOP BOAT---------------------
    // get total order item by shop boat id in today
    @Override
    public Object getTotalOrderItemByShopBoatId(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatId(shopBoatId);
    }

    // get total order item by order product id and shop boat id in 0h-3h,
    // 3h-6h,....
    @Override
    public List<Object[]> getTotalOrderItemByShopBoatIdInTimeSlot(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInTimeSlot(shopBoatId);
    }

    // get total order item by order product id and shop boat id in week
    @Override
    public Object getTotalOrderItemByShopBoatIdInWeek(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInWeek(shopBoatId);
    }

    // get total order item by order product id and shop boat id in thứ 2, thứ 3,
    // thứ 4, thứ 5, thứ 6, thứ 7, chủ nhật
    @Override
    public List<Object[]> getTotalOrderItemByShopBoatIdInDayOfWeek(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInDayOfWeek(shopBoatId);
    }

    // get total order item by order product id and shop boat id in month
    @Override
    public Object getTotalOrderItemByShopBoatIdInMonth(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInMonth(shopBoatId);
    }

    // get total order item by order product id and shop boat id in tuần 1, tuần 2,
    // tuần 3, tuần 4, tuần 5
    @Override
    public List<Object[]> getTotalOrderItemByShopBoatIdInWeekOfMonth(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInWeekOfMonth(shopBoatId);
    }

    // get total order item by order product id and shop boat id in year
    @Override
    public Object getTotalOrderItemByShopBoatIdInYear(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInYear(shopBoatId);
    }

    // get total order item by order product id and shop boat id in tháng 1, tháng
    // 2,...
    @Override
    public List<Object[]> getTotalOrderItemByShopBoatIdInMonthOfYear(int shopBoatId) {
        return productRepsitory.getTotalOrderItemByShopBoatIdInMonthOfYear(shopBoatId);
    }

    // get top 5 product by revenue in today
    @Override
    public List<Object[]> getTop5ProductByRevenueInToday(int shopBoatId) {
        return productRepsitory.getTop5ProductByRevenueInToday(shopBoatId);
    }

    // get top 5 product by revenue in week
    @Override
    public List<Object[]> getTop5ProductByRevenueInThisWeek(int shopBoatId) {
        return productRepsitory.getTop5ProductByRevenueInThisWeek(shopBoatId);
    }

    // get top 5 product by revenue in month
    @Override
    public List<Object[]> getTop5ProductByRevenueInThisMonth(int shopBoatId) {
        return productRepsitory.getTop5ProductByRevenueInThisMonth(shopBoatId);
    }

    // get top 5 product by revenue in year
    @Override
    public List<Object[]> getTop5ProductByRevenueInThisYear(int shopBoatId) {
        return productRepsitory.getTop5ProductByRevenueInThisYear(shopBoatId);
    }

    // get total price of order item by id shop boat in this month
    @Override
    public List<Object[]> getTotalPriceOrderItemByShopBoatIdInMonth() {
        return productRepsitory.getTotalPriceOrderItemByShopBoatIdInMonth();
    }

    // get total price of order item by id shop boat in this year
    @Override
    public List<Object[]> getTotalPriceOrderItemByShopBoatIdInYear() {
        return productRepsitory.getTotalPriceOrderItemByShopBoatIdInYear();
    }

    // seach product by name
    @Override
    public Page<Product> searchProductByName(String name, int page) {
        int pageSize = 8;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Product> pageResult = productRepsitory.searchProductByName(name, pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

}
