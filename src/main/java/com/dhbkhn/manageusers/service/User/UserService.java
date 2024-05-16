package com.dhbkhn.manageusers.service.User;

import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dhbkhn.manageusers.enums.Role;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;

public interface UserService {
    public User saveUser(User user);

    public List<User> getAllUsers();

    // delete user
    public void deleteUser(int id);

    public User findById(int id);

    public User findByName(String name);

    public User registerUser(User user);

    public User authenticate(User user);

    public List<ShopBoat> getShopBoatByUserId(int userId);

    public UserDetailsService userDetailsService();

    public Page<User> searchUser(String name, String username, String address, String phone_number, Role role,
            int page);

    // delete user by id
    public void deleteByUserId(int id);

    // get user by id
    public User getUserById(int id);

    // update user by id
    public void updateUserById(String name, String avatar, String phone_number, int id);
}
