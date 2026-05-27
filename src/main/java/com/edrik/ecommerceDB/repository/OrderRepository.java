package com.edrik.ecommerceDB.repository;


import com.edrik.ecommerceDB.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,UUID> {
    List<Order> findByorderName(String orderName);
    List<Order> findByorderedAt(Instant orderedAt);
    List<Order> findBypriceLessThanEqual(Long price);
    List<Order> findBypriceGreaterThanEqual(Long price);
}
