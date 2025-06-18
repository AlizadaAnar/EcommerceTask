package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.entity.AppOrder;
import com.example.ecommerceapplication.entity.AppUser;
import com.example.ecommerceapplication.exceeption.UserNotFoundException;
import com.example.ecommerceapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public void updateByIdExactData(AppUser appUser, Long id) {
        AppUser byId = findById(id);
        byId.setName(appUser.getName());

        byId.getOrders().clear(); // Remove old orders
        for (AppOrder order : appUser.getOrders()) {
            order.setAppUser(byId);
        }
        byId.setOrders(appUser.getOrders());

        userRepository.save(byId); // Save changes
    }
    
    public List<AppOrder> getUserOrdersById(Long id) {
        AppUser user = findById(id);
        return user.getOrders();
    }

    @Transactional  // Ensures Lazy Loading works inside a transaction
    public AppUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
