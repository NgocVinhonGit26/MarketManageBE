package com.dhbkhn.manageusers.controller;

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

import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.service.ShopBoat.ShopBoatService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/merchant")
public class ShopBoatController {
    @Autowired
    private ShopBoatService shopBoatService;

    public ShopBoatController(ShopBoatService shopBoatService) {
        this.shopBoatService = shopBoatService;
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
}
