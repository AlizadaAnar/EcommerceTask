package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.service.UserService;
import com.example.ecommerceapplication.entity.AppOrder;
import com.example.ecommerceapplication.entity.AppUser;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public AppUser createUser(@Valid @RequestBody AppUser appUser) {
         return userService.saveUser(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        return userService.updateUser(appUser);
    }

    @GetMapping("/get-orders/{userId}")
    public List<AppOrder> getOrders(@PathVariable Long userId) {
        return userService.getUserOrdersById(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<AppUser> getUsers() { //get all users
        return userService.getAllUsers();
    }
    
    @GetMapping("/find-by-id/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }









//    @PutMapping("/by-id/{id}")
//    public String updateById(@RequestBody AppUser appUser, @PathVariable Long id) {
//        userService.updateByIdExactData(appUser, id);
//        return "successfully updated";
//    }

//    @GetMapping("/detach/{id}")
//    public String detachUser(@PathVariable Long id) {
//        userService.detachUser(id);
//        return "User detached";
//    }
}
