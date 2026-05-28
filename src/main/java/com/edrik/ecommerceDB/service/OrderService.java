package com.edrik.ecommerceDB.service;


import com.edrik.ecommerceDB.dao.OrderDao;
import com.edrik.ecommerceDB.exception.OrderNotFoundException;
import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.model.OrderStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderDao dao;


    public OrderService(OrderDao dao){
        this.dao=dao;
    }

    public Order createOrder(Order order, HttpHeaders headers) {
        order.setCreatedBy(headers.getFirst("createdBy"));
        if(validate(order)){
            order.setId(UUID.randomUUID());
            order.setOrderedAt(Instant.now());
            order.setStatus(OrderStatus.CREATED);
        }
        return dao.createOrder(order);
    }
    public Boolean validate(Order order){
        boolean valid =true;
        if(!StringUtils.hasText(order.getOrderName())){
            valid=false;
        }
        if(order.getPrice()==null){
            valid=false;
        }
        if(!StringUtils.hasText(order.getCreatedBy())){
            valid=false;
        }
        return valid;
    }

    public List<Order> getOrders() {
        return dao.getOrders();
    }

    public Order getOrderById(UUID id){
        return dao.getOrderById(id);
    }

    public List<Order> getOrderByName(String name) {
        return dao.getOrderByName(name);
    }

    public List<Order> getOrderByDate(Instant date) {
        return dao.getOrderByDate(date);
    }

    public List<Order> getOrderByLowPrice(Long price) {
        return dao.getOrderByLowPrice(price);
    }

    public List<Order> getOrderByGreaterPrice(Long price) {
        return dao.getOrderByGreaterPrice(price);
    }

    public Order updateOrder(UUID id, Order order) {
        if(dao.getOrderById(id)==null){
            throw new OrderNotFoundException("OrderNotFound with id:"+id);
        }
        return dao.updateOrder(id,order);
    }

    public Order patchOrder(UUID id, Order order) {
        if(dao.getOrderById(id)==null){
            throw new OrderNotFoundException("OrderNotFound with id:"+id);
        }
        return dao.patchOrder(id,order);
    }

    public void deleteOrder(UUID id) {
        if(dao.getOrderById(id)==null){
            throw new OrderNotFoundException("OrderNotFound with id:"+id);
        }
        dao.deleteOrder(id);
    }
    public List<Order> getOrderWithAny(String obj) {
        try {
            UUID id=UUID.fromString(obj);
            return dao.getId(id);
        } catch (Exception e) {
        }
        try{
            Long price=Long.parseLong(obj);
            return dao.getLong(price);
        } catch (Exception e) {
        }
        try {
            OrderStatus status=OrderStatus.valueOf(obj);
            return dao.getStatus(status);
        } catch (Exception e) {
        }
        try {
            Instant date=Instant.parse(obj);
            return dao.getDate(date);
        } catch (Exception e) {
        }
        return dao.getString(obj);
    }
}
