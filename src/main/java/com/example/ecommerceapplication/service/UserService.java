package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.entity.AppOrder;
import com.example.ecommerceapplication.entity.AppUser;
import com.example.ecommerceapplication.exceeption.UserNotFoundException;
import com.example.ecommerceapplication.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public List<AppOrder> getUserOrdersById(Long userId) {
        AppUser user = findById(userId);
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










//    @Transactional
//    public void updateByIdExactData(AppUser appUser, Long id) {
//        AppUser existingUser = findById(id);
//
//        existingUser.setName(appUser.getName());
//
//        existingUser.getOrders().clear();
//
//        List<AppOrder> newOrders = appUser.getOrders();
//        if (newOrders != null) {
//            for (AppOrder order : newOrders) {
//                order.setAppUser(existingUser); // Maintain the relationship
//            }
//            existingUser.setOrders(newOrders);
//        }
//
//        userRepository.save(existingUser);
//    }


//    public void updateByIdExactData(AppUser appUser, Long id) {
//        AppUser byId = findById(id);
//        byId.setName(appUser.getName());
//
//        byId.getOrders().clear(); // Remove old orders
//        for (AppOrder order : appUser.getOrders()) {
//            order.setAppUser(byId);
//        }
//        byId.setOrders(appUser.getOrders());
//
//        userRepository.save(byId);
//    }

//    public void detachUser(Long id) {
//        AppUser user = findById(id);
//        entityManager.detach(user);
//    }

    //for detach
//    @PersistenceContext
//    private EntityManager entityManager;

}
