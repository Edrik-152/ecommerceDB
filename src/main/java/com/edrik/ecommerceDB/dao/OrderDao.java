package com.edrik.ecommerceDB.dao;

import com.edrik.ecommerceDB.exception.OrderNotFoundException;
import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderDao {
    private final OrderRepository repo;

    public OrderDao(OrderRepository repo){
        this.repo=repo;
    }
    public Order createOrder(Order order) {
        return repo.save(order);
    }

    public List<Order> getOrder() {
        return repo.findAll();
    }

    public Order getOrderById(UUID id) {
        return repo.findById(id).orElseThrow(()->new OrderNotFoundException("OrderNotFound with id:"+id));
    }
}
