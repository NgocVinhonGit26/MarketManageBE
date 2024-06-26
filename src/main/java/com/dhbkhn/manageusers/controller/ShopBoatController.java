package com.dhbkhn.manageusers.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.dhbkhn.manageusers.DTO.OrderItemDTO;
import com.dhbkhn.manageusers.DTO.OrderItemShopBoatDTO;
import com.dhbkhn.manageusers.DTO.OrderProductDTO;
import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.Product.OrderItem;
import com.dhbkhn.manageusers.model.Product.OrderProduct;
import com.dhbkhn.manageusers.model.Product.Product;
import com.dhbkhn.manageusers.service.Imgqr.ImgqrService;
import com.dhbkhn.manageusers.service.Product.ProductService;
import com.dhbkhn.manageusers.service.ShopBoat.ShopBoatService;

@CrossOrigin(origins = "https://market-manage-fgotsqtmt-ngocvinhongit26s-projects.vercel.app")
@RestController
@RequestMapping("/merchant")
public class ShopBoatController {
    @Autowired
    private ShopBoatService shopBoatService;
    private ProductService productService;
    private ImgqrService imgqrService;

    public ShopBoatController(ShopBoatService shopBoatService, ProductService productService,
            ImgqrService imgqrService) {
        this.shopBoatService = shopBoatService;
        this.productService = productService;
        this.imgqrService = imgqrService;
    }

    // get all shop boats
    // @GetMapping("/getListShopBoats")
    // public List<ShopBoatDTO> getListShopBoats() {
    // return shopBoatService.getAllShopBoats();
    // }

    // get all shop boats with pagination
    @GetMapping("/getAllShopBoatWithPagination/{page}")
    public List<ShopBoatDTO> getAllShopBoatWithPagination(@PathVariable int page) {
        List<ShopBoatDTO> listShopBoatDTOs = new ArrayList<>();
        Page<Object[]> pageResult = shopBoatService.getAllShopBoatWithPagination(page);
        for (Object[] row : pageResult.getContent()) {
            ShopBoatDTO shopBoatDTO = new ShopBoatDTO();
            shopBoatDTO.setId((int) row[0]);
            shopBoatDTO.setName((String) row[1]);
            shopBoatDTO.setAddress((String) row[2]);
            // shopBoatDTO.setOwner((int)row[3]);
            shopBoatDTO.setDescription((String) row[4]);
            shopBoatDTO.setAvatar((String) row[5]);
            shopBoatDTO.setPhoneNumber((String) row[6]);
            shopBoatDTO.setStatus((int) row[7]);
            shopBoatDTO.setCode((String) row[8]);
            shopBoatDTO.setOwnerName((String) row[9]);
            listShopBoatDTOs.add(shopBoatDTO);
        }
        return listShopBoatDTOs;
    }

    // get shop boat by id
    @GetMapping("/getShopBoatById/{id}")
    public ShopBoat getShopBoatById(@PathVariable int id) {
        return shopBoatService.getShopBoatById(id);
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

    // search shop boat
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

    // get Total Page
    @GetMapping("/getTotalPage/{page}")
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

    // update shop boat by id
    @PostMapping("/updateShopBoatById/{id}")
    public ResponseEntity<ShopBoat> updateShopBoatById(@PathVariable int id, @RequestBody ShopBoat shopBoat) {
        shopBoatService.updateShopBoatById(shopBoat.getName(), shopBoat.getDescription(), shopBoat.getType(),
                shopBoat.getAvatar(), id);
        ShopBoat updatedShopBoat = shopBoatService.getShopBoatById(id);
        if (updatedShopBoat != null) {
            System.out.println("Shopboat: update successfully!");
            return ResponseEntity.ok(updatedShopBoat);
        }
        System.out.println("Shopboat: update failed!");
        return ResponseEntity.notFound().build();
    }

    // ---------------------- MANAGE PRODUCT----------------------------

    // create new product
    @PostMapping("/createNewProduct")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        productService.createNewProduct(product.getName(), product.getSlug(), product.getDescription(),
                product.getPrice(), product.getSale(), product.getCountInStock(), product.getImage(), product.getUnit(),
                product.getCategory(), product.getShopBoatId(), product.getCreated_at(), product.getUpdated_at(),
                product.getVideoInfor());
        Product newProduct = productService.findBySlug(product.getSlug());
        if (newProduct != null) {
            System.out.println("Product: create successfully!");
            return ResponseEntity.ok(newProduct);
        }
        System.out.println("Product: create failed!");
        return ResponseEntity.notFound().build();
    }

    // update a product by id
    @PostMapping("/updateProductById/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product product) {
        productService.updateProductById(product.getName(), product.getSlug(), product.getDescription(),
                product.getPrice(), product.getSale(), product.getCountInStock(), product.getImage(), product.getUnit(),
                product.getCategory(), product.getUpdated_at(), product.getVideoInfor(), id);
        Product updatedProduct = productService.findBySlug(product.getSlug());
        if (updatedProduct != null) {
            System.out.println("Product: update successfully!");
            return ResponseEntity.ok(updatedProduct);
        }
        System.out.println("Product: update failed!");
        return ResponseEntity.notFound().build();
    }

    // search product by name, priceFrom, PriceTo ,CountInStock, Category, sale
    @GetMapping("/searchProduct/{page}/{shopBoatId}")
    public List<Product> searchProduct(
            @PathVariable int page,
            @PathVariable int shopBoatId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Boolean countInStock,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double sale) {
        Page<Product> pageResult = productService.searchProduct(name, priceFrom, priceTo, countInStock, category, sale,
                page, shopBoatId);
        return pageResult.getContent();
    }

    // get total page
    @GetMapping("/getTotalPage/{page}/{shopBoatId}")
    public int getTotalPage(
            @PathVariable int shopBoatId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Boolean countInStock,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double sale,
            @PathVariable int page) {
        Page<Product> pageResult = productService.searchProduct(name, priceFrom, priceTo, countInStock, category,
                sale, page, shopBoatId);
        return pageResult.getTotalPages();
    }

    // get all order product
    @GetMapping("/getAllListOrderItem/{page}")
    public List<OrderItemShopBoatDTO> getAllListOrderItem(@RequestParam int shopBoatId, @PathVariable int page) {
        Page<Object[]> pageResult = productService.getAllListOrderItem(shopBoatId, page);
        List<OrderItemShopBoatDTO> listOrderItemDTO = new ArrayList<>();
        for (Object[] objects : pageResult.getContent()) {
            OrderItemShopBoatDTO orderItemDTO = new OrderItemShopBoatDTO();
            orderItemDTO.setId((int) objects[0]);
            orderItemDTO.setStatus((String) objects[1]);
            orderItemDTO.setProductId((int) objects[2]);
            orderItemDTO.setOrderProductId((int) objects[3]);
            orderItemDTO.setShopBoatId((int) objects[4]);
            orderItemDTO.setQuantity((int) objects[5]);
            orderItemDTO.setPrice((java.math.BigDecimal) objects[6]);
            orderItemDTO.setSale((java.math.BigDecimal) objects[7]);
            orderItemDTO.setUserName((String) objects[8]);
            orderItemDTO.setUserNumberPhone((String) objects[9]);
            orderItemDTO.setUserAddress((String) objects[10]);
            listOrderItemDTO.add(orderItemDTO);
        }
        return listOrderItemDTO;
    }

    // get order item by order product id
    @GetMapping("/getOrderItemByOrderProductId/{shopBoatId}/{orderProductId}")
    public List<OrderItemDTO> getOrderItemByOrderProductId(@PathVariable int shopBoatId,
            @PathVariable int orderProductId) {
        List<OrderItemDTO> listOrderItemDTO = new ArrayList<>();
        List<Object[]> orderItemData = productService.getOrderItemByOrderProductId(shopBoatId, orderProductId);
        for (Object[] row : orderItemData) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setId((int) row[0]);
            orderItemDTO.setStatus((String) row[1]);
            orderItemDTO.setProductId((int) row[2]);
            orderItemDTO.setOrderProductId((int) row[3]);
            orderItemDTO.setShopBoatId((int) row[4]);
            orderItemDTO.setQuantity((int) row[5]);
            orderItemDTO.setPrice((java.math.BigDecimal) row[6]);
            orderItemDTO.setSale((java.math.BigDecimal) row[7]);
            orderItemDTO.setProductName((String) row[8]);
            orderItemDTO.setProductAvatar((String) row[9]);
            listOrderItemDTO.add(orderItemDTO);
        }
        return listOrderItemDTO;
    }

    // get total page order product
    @GetMapping("/getTotalPageOrderItem/{page}")
    public int getTotalPageOrderItem(@RequestParam int shopBoatId, @PathVariable int page) {
        Page<Object[]> pageResult = productService.getAllListOrderItem(shopBoatId, page);
        return pageResult.getTotalPages();
    }

    // get shop boat by id user
    @GetMapping("/getShopBoatByIdUser/{idUser}")
    public ShopBoat getShopBoatByIdUser(@PathVariable int idUser) {
        return shopBoatService.getShopBoatByIdUser(idUser);
    }

    // update status order product by id
    @PostMapping("/updateStatusOrderItemById")
    public ResponseEntity<String> updateStatusOrderItemById(
            @RequestBody OrderItem orderItem) {
        productService.updateStatusOrderItemById(orderItem.getStatus(), orderItem.getOrderProductId(),
                orderItem.getShopBoatId());

        // return ResponseEntity.ok("Update status successfully!");
        return ResponseEntity.ok(orderItem.getStatus());
    }

    // get order product b
    @GetMapping("/searchOrderProduct/{page}/{shopBoatId}")
    public List<OrderProductDTO> searchOrderProduct(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String customerPhoneNumber,
            @RequestParam(required = false) String customerAddress,
            @RequestParam(required = false) Timestamp dateFrom,
            @RequestParam(required = false) Timestamp dateTo,
            @RequestParam(required = false) BigDecimal totalFrom,
            @RequestParam(required = false) BigDecimal totalTo,
            @RequestParam(required = false) String status,
            @PathVariable int shopBoatId,
            @PathVariable int page) {
        Page<Object[]> pageResult = productService.searchOrderProduct(customerName, customerPhoneNumber,
                customerAddress,
                dateFrom, dateTo, totalFrom, totalTo, status, shopBoatId, page);
        List<OrderProductDTO> listOrderProduct = new ArrayList<>();
        for (Object[] objects : pageResult.getContent()) {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setId((int) objects[0]);
            orderProductDTO.setStatus((String) objects[1]);
            orderProductDTO.setPaymentMethod((String) objects[2]);
            orderProductDTO.setTotal((java.math.BigDecimal) objects[3]);
            // orderProductDTO.setShopBoatId((int) objects[4]);
            orderProductDTO.setCustomer((int) objects[4]);
            orderProductDTO.setCreatedAt((java.sql.Timestamp) objects[5]);
            orderProductDTO.setUpdatedAt((java.sql.Timestamp) objects[6]);
            orderProductDTO.setUserName((String) objects[7]);
            orderProductDTO.setUserNumberPhone((String) objects[8]);
            orderProductDTO.setUserAddress((String) objects[9]);
            orderProductDTO.setStatusOrderItems((String) objects[10]);
            listOrderProduct.add(orderProductDTO);
        }
        return listOrderProduct;
    }

    // get total page order product
    @GetMapping("/getTotalPageOrderProducts/{page}/{shopBoatId}")
    public int getTotalPageOrderProducts(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String customerPhoneNumber,
            @RequestParam(required = false) String customerAddress,
            @RequestParam(required = false) Timestamp dateFrom,
            @RequestParam(required = false) Timestamp dateTo,
            @RequestParam(required = false) BigDecimal totalFrom,
            @RequestParam(required = false) BigDecimal totalTo,
            @RequestParam(required = false) String status,
            @PathVariable int shopBoatId,
            @PathVariable int page) {
        Page<Object[]> pageResult = productService.searchOrderProduct(customerName, customerPhoneNumber,
                customerAddress, dateFrom, dateTo, totalFrom, totalTo, status, shopBoatId, page);
        return pageResult.getTotalPages();
    }

    // get order product by id shopboat and id order item
    @GetMapping("/getAllListOrderProduct/{shopBoatId}/{page}")
    public List<OrderProductDTO> getAllListOrderProduct(@PathVariable int shopBoatId, @PathVariable int page) {
        Page<Object[]> pageResult = productService.getAllListOrderProduct(shopBoatId, page);
        List<OrderProductDTO> listOrderProduct = new ArrayList<>();
        for (Object[] objects : pageResult.getContent()) {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setId((int) objects[0]);
            orderProductDTO.setStatus((String) objects[1]);
            orderProductDTO.setPaymentMethod((String) objects[2]);
            orderProductDTO.setTotal((java.math.BigDecimal) objects[3]);
            // orderProductDTO.setShopBoatId((int) objects[4]);
            orderProductDTO.setCustomer((int) objects[4]);
            orderProductDTO.setCreatedAt((java.sql.Timestamp) objects[5]);
            orderProductDTO.setUpdatedAt((java.sql.Timestamp) objects[6]);
            orderProductDTO.setUserName((String) objects[7]);
            orderProductDTO.setUserNumberPhone((String) objects[8]);
            orderProductDTO.setUserAddress((String) objects[9]);
            orderProductDTO.setStatusOrderItems((String) objects[10]);
            listOrderProduct.add(orderProductDTO);
        }

        return listOrderProduct;
    }

    // get total page order product
    @GetMapping("/getTotalPageOrderProduct/{shopBoatId}/{page}")
    public int getTotalPageOrderProduct(@PathVariable int shopBoatId, @PathVariable int page) {
        Page<Object[]> pageResult = productService.getAllListOrderProduct(shopBoatId, page);
        return pageResult.getTotalPages();
    }

    // get total order item by order product id and shop boat id in today
    @GetMapping("/getTotalOrderItemByShopBoatId/{shopBoatId}")
    public Object getTotalOrderItemByShopBoatId(@PathVariable int shopBoatId) {
        Object result = productService.getTotalOrderItemByShopBoatId(shopBoatId);

        return result;
    }

    // get total order item by order product id and shop boat id in 0h-3h,
    // 3h-6h,....
    @GetMapping("/getTotalOrderItemByShopBoatIdInTimeSlot/{shopBoatId}")
    public List<Object[]> getTotalOrderItemByShopBoatIdInTimeSlot(@PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTotalOrderItemByShopBoatIdInTimeSlot(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in week
    @GetMapping("/getTotalOrderItemByShopBoatIdInWeek/{shopBoatId}")
    public Object getTotalOrderItemByShopBoatIdInWeek(@PathVariable int shopBoatId) {
        Object result = productService.getTotalOrderItemByShopBoatIdInWeek(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in thứ 2, thứ 3,
    // thứ 4, thứ 5, thứ 6, thứ 7, chủ nhật
    @GetMapping("/getTotalOrderItemByShopBoatIdInDayOfWeek/{shopBoatId}")
    public List<Object[]> getTotalOrderItemByShopBoatIdInDayOfWeek(@PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTotalOrderItemByShopBoatIdInDayOfWeek(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in month
    @GetMapping("/getTotalOrderItemByShopBoatIdInMonth/{shopBoatId}")
    public Object getTotalOrderItemByShopBoatIdInMonth(@PathVariable int shopBoatId) {
        Object result = productService.getTotalOrderItemByShopBoatIdInMonth(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in tuần 1, tuần 2,
    // tuần 3, tuần 4, tuần 5
    @GetMapping("/getTotalOrderItemByShopBoatIdInWeekOfMonth/{shopBoatId}")
    public List<Object[]> getTotalOrderItemByShopBoatIdInWeekOfMonth(@PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTotalOrderItemByShopBoatIdInWeekOfMonth(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in year
    @GetMapping("/getTotalOrderItemByShopBoatIdInYear/{shopBoatId}")
    public Object getTotalOrderItemByShopBoatIdInYear(@PathVariable int shopBoatId) {
        Object result = productService.getTotalOrderItemByShopBoatIdInYear(shopBoatId);
        return result;
    }

    // get total order item by order product id and shop boat id in tháng 1, tháng
    // 2,...
    @GetMapping("/getTotalOrderItemByShopBoatIdInMonthOfYear/{shopBoatId}")
    public List<Object[]> getTotalOrderItemByShopBoatIdInMonthOfYear(@PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTotalOrderItemByShopBoatIdInMonthOfYear(shopBoatId);
        return result;
    }

    // get top 5 product by revenue in today
    @GetMapping("/getTop5ProductByRevenueInToday/{shopBoatId}")
    public List<Object[]> getTop5ProductByRevenueInToday(
            @PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTop5ProductByRevenueInToday(shopBoatId);
        return result;
    }

    // get top 5 product by revenue in week
    @GetMapping("/getTop5ProductByRevenueInThisWeek/{shopBoatId}")
    public List<Object[]> getTop5ProductByRevenueInThisWeek(
            @PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTop5ProductByRevenueInThisWeek(shopBoatId);
        return result;
    }

    // get top 5 product by revenue in month
    @GetMapping("/getTop5ProductByRevenueInThisMonth/{shopBoatId}")
    public List<Object[]> getTop5ProductByRevenueInThisMonth(
            @PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTop5ProductByRevenueInThisMonth(shopBoatId);
        return result;
    }

    // get top 5 product by revenue in year
    @GetMapping("/getTop5ProductByRevenueInThisYear/{shopBoatId}")
    public List<Object[]> getTop5ProductByRevenueInThisYear(
            @PathVariable int shopBoatId) {
        List<Object[]> result = productService.getTop5ProductByRevenueInThisYear(shopBoatId);
        return result;
    }

    // get img qr by id
    @GetMapping("/getImgqrById")
    public String getImgqrById() {
        return imgqrService.getImgqrById();
    }

}
