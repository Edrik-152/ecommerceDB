package com.edrik.ecommerceDB.controller;


import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
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
    public List<Order> getOrders(){
        return service.getOrders();
    }
    @GetMapping("/get-order/id/{id}")
    public Order getOrderById(@PathVariable UUID id){
        return service.getOrderById(id);
    }

    @GetMapping("/get-order/name/{name}")
    public List<Order> getOrderByName(@PathVariable String name){
        return service.getOrderByName(name);
    }
    @GetMapping("/get-order/date/{date}")
    public List<Order> getOrderByDate(@PathVariable Instant date){
        return service.getOrderByDate(date);
    }
    @GetMapping("/get-order/low-price/{price}")
    public List<Order> getOrderByLowPrice(@PathVariable Long price){
        return service.getOrderByLowPrice(price);
    }
    @GetMapping("/get-order/high-price/{price}")
    public List<Order> getOrderByGreaterPrice(@PathVariable Long price){
        return service.getOrderByGreaterPrice(price);
    }
    @PutMapping("/update-order/{id}")
    public Order updateOrder(@PathVariable UUID id,@RequestBody Order order){
        return service.updateOrder(id,order);
    }
    @PatchMapping("/patch-order/{id}")
    public Order patchOrder(@PathVariable UUID id,@RequestBody Order order){
        return service.patchOrder(id,order);
    }
    @DeleteMapping("/delete-order/{id}")
    public void deleteOrder(@PathVariable UUID id){
        service.deleteOrder(id);
    }
}
