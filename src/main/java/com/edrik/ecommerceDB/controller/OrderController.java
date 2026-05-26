package com.edrik.ecommerceDB.controller;


import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){
        this.service=service;
    }

    @PostMapping("/create-order")
    public Order createOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers){
        return service.createOrder(order,headers);
    }
    @GetMapping("/get-orders")
    public List<Order> getOrder(){
        return service.getOrder();
    }
    @GetMapping("/get-order/{id}")
    public Order getOrderById(@PathVariable UUID id){
        return service.getOrderById(id);
    }
}
