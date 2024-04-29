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

import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.repository.ProductRepsitory;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepsitory productRepsitory;

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
    public Page<Product> searchProduct(String name, Double priceFrom, Double priceTo, Integer countInStock,
            String category, Double sale, int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Product> pageResult = productRepsitory.searchProduct(name, priceFrom, priceTo, countInStock, category,
                    sale, pageable);
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

    // insert order product
    @Transactional
    @Override
    public void createOrderProduct(String status, String paymentMethod, BigDecimal total,
            int shopBoatId, int customer, Timestamp createdAt, Timestamp updatedAt) {
        productRepsitory.createOrderProduct(status, paymentMethod, total, shopBoatId, customer, createdAt, updatedAt);
    }

    // get last order product by id customer
    @Override
    public int getLastOrderProduct(int userId) {
        return productRepsitory.getLastOrderProduct(userId);
    }

    // insert order item
    @Transactional
    @Override
    public void insertOrderItem(int productId, int orderProductId, int quantity, BigDecimal price, BigDecimal sale) {
        productRepsitory.insertOrderItem(productId, orderProductId, quantity, price, sale);
    }

    // get all order product
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

    // get order product by id
    @Override
    public List<Object[]> getOrderProductById(int id) {
        return productRepsitory.getOrderProductById(id);
    }

    // update status order product by id
    @Override
    @Transactional
    public void updateStatusOrderProductById(String status, int id) {
        productRepsitory.updateStatusOrderProductById(status, id);
    }

    // get order item by order product id
    @Override
    public List<Object[]> getOrderItemByOrderProductId(int orderProductId) {
        return productRepsitory.getOrderItemByOrderProductId(orderProductId);
    }
}
