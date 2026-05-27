package com.edrik.ecommerceDB.dao;

import com.edrik.ecommerceDB.exception.OrderNotFoundException;
import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
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

    public List<Order> getOrders() {
        return repo.findAll();
    }

    public Order getOrderById(UUID id) {
        return repo.findById(id).orElseThrow(()->new OrderNotFoundException("OrderNotFound with id:"+id));
    }

    public List<Order> getOrderByName(String name) {
        List<Order> list= repo.findByorderName(name);
        if(list.isEmpty()){
            throw new OrderNotFoundException("OrderNotFound with name:"+name);
        }
        return list;
    }

    public List<Order> getOrderByDate(Instant date) {
        List<Order> list=repo.findByorderedAt(date);
        if (list.isEmpty()){
            throw new OrderNotFoundException("OrderNotFound with date:"+date);
        }
        return list;
    }

    public List<Order> getOrderByLowPrice(Long price) {
        List<Order> list=repo.findBypriceLessThanEqual(price);
        if(list.isEmpty()){
            throw new OrderNotFoundException("No Product At This Price");
        }
        return list;
    }

    public List<Order> getOrderByGreaterPrice(Long price) {
        List<Order> list=repo.findBypriceGreaterThanEqual(price);
        if(list.isEmpty()){
            throw new OrderNotFoundException("No Product At This Price");
        }
        return list;
    }

    public Order updateOrder(UUID id,Order order) {
        Order ord =getOrderById(id);
        ord.setOrderName(order.getOrderName());
        ord.setCreatedBy(order.getCreatedBy());
        ord.setPrice(order.getPrice());
        return repo.save(ord);
    }

    public Order patchOrder(UUID id, Order order) {
        Order ord=getOrderById(id);
        if(order.getOrderName()!=null){
            ord.setOrderName(order.getOrderName());
        }
        if(order.getPrice()!=null){
            ord.setPrice(order.getPrice());
        }
        return repo.save(ord);
    }

    public void deleteOrder(UUID id) {
        repo.deleteById(id);
    }
}
