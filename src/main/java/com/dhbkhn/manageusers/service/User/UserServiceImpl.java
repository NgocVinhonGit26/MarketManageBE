package com.dhbkhn.manageusers.service.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhbkhn.manageusers.enums.Role;
import com.dhbkhn.manageusers.model.ShopBoat;
import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    // String dbUrl = "jdbc:mysql://localhost:3306/";
    // String user = "root";
    // String pass = "26072001";
    // Connection connection = DriverManager.getConnection(null, null, null)

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        // TODO Auto-generated constructor stub
    }

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(String name, String address, int id) {
        // TODO Auto-generated method stub
        userRepository.updateUserById(name, address, id);
    }

    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String name) {
        // TODO Auto-generated method stub
        return userRepository.findByName(name);
    }

    @Override
    public User authenticate(User user) {
        // System.out.println(userRepository.findByNameAndPassword(user.getName(),
        // user.getPassword()).orElse(null));
        Optional<User> user1Optional = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
        if (user1Optional.isPresent()) {
            return user1Optional.get();
        }
        return null;

        // return userRepository.findByNameAndPassword(user.getName(),
        // user.getPassword()).orElse(null);
    }

    @Override
    public User registerUser(User user) {
        // TODO Auto-generated method stub
        if (user.getUsername() == null || user.getPassword() == null) {
            return null;
        }
        return userRepository.save(user);

    }

    @Override
    public List<ShopBoat> getShopBoatByUserId(int userId) {
        List<Object[]> list = userRepository.getShopBoatByUserId(userId);
        List<ShopBoat> listShopBoats = new ArrayList<>();
        for (Object[] row : list) {
            ShopBoat shopBoat = new ShopBoat();
            shopBoat.setId((int) row[0]);
            shopBoat.setName((String) row[1]);
            shopBoat.setAddress((String) row[2]);
            shopBoat.setOwner((int) row[3]);
            shopBoat.setDescription((String) row[4]);
            shopBoat.setAvatar((String) row[5]);
            shopBoat.setPhoneNumber((String) row[6]);
            shopBoat.setType((String) row[7]);
            shopBoat.setStatus((int) row[8]);
            shopBoat.setCode((String) row[9]);
            listShopBoats.add(shopBoat);
        }
        return listShopBoats;
    }

    @Override
    public UserDetailsService userDetailsService() {
        // TODO Auto-generated method stub
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " +
                                username));
            }
        };
    }

    @Override
    public Page<User> searchUser(String name, String username, String address, String phone_number, Role role,
            int page) {
        int pageSize = 5;
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return userRepository.searchUser(name, username, address, phone_number, role, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }

    }

    @Override
    @Transactional
    public void deleteByUserId(int id) {
        // TODO Auto-generated method stub
        userRepository.deleteByUserId(id);
    }

    @Override
    public User getUserById(int id) {
        // TODO Auto-generated method stub
        return userRepository.getUserById(id);
    }

}
