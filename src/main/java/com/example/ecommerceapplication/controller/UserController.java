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

    @PostMapping("/add") //create user
    public AppUser createUser(@Valid @RequestBody AppUser appUser) {
         return userService.saveUser(appUser);
    }

    @PutMapping("/{id}") //here we use for merging new orders
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        return userService.updateUser(appUser);
    }

    @PutMapping("/by-id/{id}") //here we update exactly order
    public String updateById(@RequestBody AppUser appUser, @PathVariable Long id) {
        userService.updateByIdExactData(appUser, id);
        return "successfully updated";
    }

    @GetMapping("/get-orders/{id}") //we get orders of user
    public List<AppOrder> getOrders(@PathVariable Long id) {
        return userService.getUserOrdersById(id);
    }

    @DeleteMapping("/{id}")  //delete user by user's id
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
}
