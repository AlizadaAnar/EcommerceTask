package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.entity.AppOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends JpaRepository<AppOrder, Long> {

}
