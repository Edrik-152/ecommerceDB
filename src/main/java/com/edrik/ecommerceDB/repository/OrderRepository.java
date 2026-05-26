package com.edrik.ecommerceDB.repository;


import com.edrik.ecommerceDB.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,UUID> {
}
