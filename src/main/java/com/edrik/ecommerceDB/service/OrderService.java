package com.edrik.ecommerceDB.service;


import com.edrik.ecommerceDB.dao.OrderDao;
import com.edrik.ecommerceDB.model.Order;
import com.edrik.ecommerceDB.model.OrderStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            order.setOrderedAt(new Date());
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

    public List<Order> getOrder() {
        return dao.getOrder();
    }

    public Order getOrderById(UUID id){
        return dao.getOrderById(id);
    }

}
