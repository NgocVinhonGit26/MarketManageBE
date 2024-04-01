package com.dhbkhn.manageusers.service.ShopBoat;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;

public interface ShopBoatService {

    public List<ShopBoatDTO> getAllShopBoats();

    public Page<Object[]> getAllShopBoatWithPagination(int page);

    public ShopBoat getShopBoatById(int id);

    public void updateStatusById(int status, int id);

    public Page<Object[]> searchShopBoat(String name, String code, String phoneNumber, String type, Integer status,
            int page);

    public void updateShopBoatById(String name, String description, String type, String avatar, int id);

}
