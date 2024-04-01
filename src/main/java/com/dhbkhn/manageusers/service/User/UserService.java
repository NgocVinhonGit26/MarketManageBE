package com.dhbkhn.manageusers.service.User;

import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;

public interface UserService {
    public User saveUser(User user);

    public List<User> getAllUsers();

    // delete user
    public void deleteUser(int id);

    // update user
    public void updateUser(String name, String address, int id);

    public User findById(int id);

    public User findByName(String name);

    public User registerUser(User user);

    public User authenticate(User user);

    public List<ShopBoat> getShopBoatByUserId(int userId);

    public UserDetailsService userDetailsService();
}
