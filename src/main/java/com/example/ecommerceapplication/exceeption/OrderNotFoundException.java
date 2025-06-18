package com.example.ecommerceapplication.exceeption;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super("order nor found with id: " + id);
    }
}
