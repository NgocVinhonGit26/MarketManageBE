package com.dhbkhn.manageusers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.enums.Role;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        // add user
        public User save(User user);

        @Query("SELECT u FROM User u WHERE u.id = ?1")
        public User findById(int id);

        @Query("SELECT u FROM User u WHERE u.name = ?1")
        public User findByName(String name);

        // update user
        @Transactional
        @Modifying
        // @Query("UPDATE User u SET u.name = ?1, u.address = ?2, u.password = ?3
        // WHERE
        // u.id = ?4")
        @Query("UPDATE User u SET u.name = :name, u.address = :address WHERE u.id = :id")
        public void updateUserById(
                        @Param("name") String name,
                        @Param("address") String address,
                        @Param("id") int id);

        // login by name and password use Optional
        Optional<User> findByNameAndPassword(String name, String password);

        // get shop boat by user id
        @Query(value = "SELECT * FROM ShopBoat WHERE owner = :id", nativeQuery = true)
        public List<Object[]> getShopBoatByUserId(@Param("id") Integer id);

        // public Optional<User> findByUsername(String Username);

        public User findByRole(Role role);

        Optional<User> findByUsername(String username);

        // search user by name, username,address, phone_number,role
        @Query("SELECT u FROM User u " +
                        "WHERE " +
                        "u.isdeleted = FALSE AND " +
                        "(:name IS NULL OR u.name like %:name%) AND " +
                        "(:username IS NULL OR u.username = :username) AND " +
                        "(:address IS NULL OR u.address like %:address%) AND " +
                        "(:phone_number IS NULL OR u.phone_number = :phone_number) AND " +
                        "(:role IS NULL OR u.role = :role)")
        Page<User> searchUser(
                        @Param("name") String name,
                        @Param("username") String username,
                        @Param("address") String address,
                        @Param("phone_number") String phone_number,
                        @Param("role") Role role,
                        Pageable pageable);

        // delete user by id
        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.isdeleted = TRUE WHERE u.id = :id")
        public void deleteByUserId(@Param("id") int id);

        // get user by id
        @Query("SELECT u FROM User u WHERE u.id = :id")
        public User getUserById(@Param("id") int id);

        // update user by id
        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.name = :name, u.address = :address, u.phone_number = :phone_number WHERE u.id = :id")
        public void updateUserById(
                        @Param("name") String name,
                        @Param("address") String address,
                        @Param("phone_number") String phone_number,
                        @Param("id") int id);

}
