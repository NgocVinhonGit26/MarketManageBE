package com.dhbkhn.manageusers.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhbkhn.manageusers.DTO.ShopBoatDTO;
import com.dhbkhn.manageusers.model.ShopBoat;

@Repository
public interface ShopBoatRepository
                extends JpaRepository<ShopBoat, Integer> {
        // save shop boat
        // public ShopBoat save(ShopBoat shopBoat);
        @Modifying
        @Query(value = "INSERT INTO shopboat (name, address, owner, description, avatar, phone_number, status, code, type) "
                        + "VALUES (:name, :address, :owner, :description, :avatar, :phoneNumber, :status, :code, :type)", nativeQuery = true)
        void createNewSB(
                        @Param("name") String name,
                        @Param("address") String address,
                        @Param("owner") int owner,
                        @Param("description") String description,
                        @Param("avatar") String avatar,
                        @Param("phoneNumber") String phoneNumber,
                        @Param("status") int status,
                        @Param("code") String code,
                        @Param("type") String type);

        // find by code
        @Query(value = "SELECT * FROM shopboat WHERE code = :code", nativeQuery = true)
        public ShopBoat findByCode(@Param("code") String code);
        // get all shop boat
        // use query to get all shop boat

        // get all shop boat
        // @Query(value = "SELECT * FROM ShopBoat", nativeQuery = true)
        @Query(value = "SELECT shopboat.*, user.name AS owner_name FROM shopboat INNER JOIN user ON shopboat.owner=user.id", nativeQuery = true)
        public List<Object[]> getListShopBoats();

        // get all shop boat with pagination
        @Query(value = "SELECT shopboat.*, user.name AS owner_name FROM shopboat INNER JOIN user ON shopboat.owner=user.id", nativeQuery = true)
        Page<Object[]> findAllCC(Pageable pageable);

        // find shop boat by id
        // @Modifying
        @Query(value = "SELECT * FROM shopboat WHERE id = :id", nativeQuery = true)
        public ShopBoat findById(int id);

        // update status by id
        // @Query(value = "UPDATE ShopBoat SET status = ?1 WHERE id = ?2", nativeQuery =
        // true)
        @Modifying
        @Query(value = "UPDATE shopboat sb SET sb.status = :status WHERE sb.id = :id", nativeQuery = true)
        public void updateStatusById(
                        @Param("status") int status,
                        @Param("id") int id);

        // Query search by Name or status or code
        @Query(value = "SELECT sb.*, u.name AS owner_name " +
                        "FROM shopboat sb " +
                        "INNER JOIN user u ON sb.owner = u.id " +
                        "WHERE " +
                        "(:name IS NULL OR sb.name like %:name%) AND " +
                        "(:code IS NULL OR sb.code = :code) AND " +
                        "(:phoneNumber IS NULL OR sb.phone_number = :phoneNumber) AND " +
                        "(:type IS NULL OR sb.type like %:type%) AND " +
                        "(:status IS NULL OR sb.status = :status)", nativeQuery = true)
        public Page<Object[]> searchShopBoat(
                        @Param("name") String name,
                        @Param("code") String code,
                        @Param("phoneNumber") String phoneNumber,
                        @Param("type") String type,
                        @Param("status") Integer status,
                        Pageable pageable);

        // update name, description, type, avatar by id
        @Modifying
        @Query(value = "UPDATE shopboat sb SET sb.name = :name, sb.description = :description, sb.type = :type, sb.avatar = :avatar WHERE sb.id = :id", nativeQuery = true)
        public void updateShopBoatById(
                        @Param("name") String name,
                        @Param("description") String description,
                        @Param("type") String type,
                        @Param("avatar") String avatar,
                        @Param("id") int id);

        // get shop boat by id user
        @Query(value = "SELECT * FROM shopboat WHERE owner = :id", nativeQuery = true)
        public ShopBoat getShopBoatByIdUser(@Param("id") int id);

}
