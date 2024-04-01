package com.dhbkhn.manageusers.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.service.User.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User added successfully";
    }

    @GetMapping("/getListUser")
    public List<User> getListUser() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@RequestBody User user, @PathVariable int id) {
        userService.updateUser(user.getName(), user.getAddress(), id);
        return "User updated successfully";
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PostMapping("/auth/registerUser")
    public User registerUser(@RequestBody User user) {
        User registerUser = userService.registerUser(user);
        if (registerUser == null) {
            System.out.println("Register failed, user already exists!");
            return null;
        }
        return registerUser;
    }

    @PostMapping("/login")
    public User authenticate(@RequestBody User user) {
        return userService.authenticate(user);
    }

    @GetMapping("/{id}/shopboat")
    public List<ShopBoat> getShopBoatByUserId(@PathVariable int id) {
        return userService.getShopBoatByUserId(id);
    }

}
