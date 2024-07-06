package com.dhbkhn.manageusers.service.ShopBoat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.repository.ShopBoatRepository;

@Service
public class ShopBoatServiceImpl implements ShopBoatService {

    private ShopBoatRepository shopBoatRepository;

    @Autowired
    public ShopBoatServiceImpl(ShopBoatRepository shopBoatRepository) {
        this.shopBoatRepository = shopBoatRepository;
    }

    // save shop boat
    @Override
    @Transactional
    public ShopBoat createNewSB(ShopBoat shopBoat) {
        shopBoatRepository.createNewSB(shopBoat.getName(), shopBoat.getAddress(), shopBoat.getOwner(),
                shopBoat.getDescription(), shopBoat.getAvatar(), shopBoat.getPhoneNumber(),
                shopBoat.getStatus(), shopBoat.getCode(), shopBoat.getType());
        // Assuming there's a method to find the shopBoat by the unique code or other
        // identifier
        return shopBoatRepository.findByCode(shopBoat.getCode());
    }

    @Override
    public List<ShopBoatDTO> getAllShopBoats() {
        List<Object[]> listShopBoats = shopBoatRepository.getListShopBoats();
        List<ShopBoatDTO> listShopBoatDTOs = new ArrayList<>();
        for (Object[] row : listShopBoats) {
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

    @Override
    public Page<Object[]> getAllShopBoatWithPagination(int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Object[]> pageResult = shopBoatRepository.findAllCC(pageable);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public ShopBoat getShopBoatById(int id) {
        return shopBoatRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateStatusById(int status, int id) {
        shopBoatRepository.updateStatusById(status, id);
    }

    @Override
    public Page<Object[]> searchShopBoat(String name, String code, String phoneNumber, String type, Integer status,
            int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return shopBoatRepository.searchShopBoat(name, code, phoneNumber, type, status, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    @Transactional
    public void updateShopBoatById(String name, String description, String type, String avatar, int id) {
        shopBoatRepository.updateShopBoatById(name, description, type, avatar, id);
    }

    // get shop boat by id user
    @Override
    public ShopBoat getShopBoatByIdUser(int idUser) {
        return shopBoatRepository.getShopBoatByIdUser(idUser);
    }

}
