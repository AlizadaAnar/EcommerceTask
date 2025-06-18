package com.example.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@JsonIgnoreProperties({"appOrders"}) // Prevent serialization of appOrders
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "appUser", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)//default is eager
    @JsonIgnore
    private List<AppOrder> appOrders;

//    @JsonIgnore //(in eager it explicitly call getOrders)
    public List<AppOrder> getOrders() {
        return appOrders;
    }

    public void setOrders(List<AppOrder> appOrders) {
        this.appOrders = appOrders;
        if (appOrders != null) {
            for (AppOrder order : appOrders) {
                order.setAppUser(this);
            }
        }
    }

    public AppUser() {
    }

    public AppUser(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + appOrders +
                '}';
    }


    //    public void setOrders(List<AppOrder> appOrders) {
//        this.appOrders = appOrders;
//    }

    //    public List<AppOrder> getOrders() {
//        return appOrders;
//    }
//
}
