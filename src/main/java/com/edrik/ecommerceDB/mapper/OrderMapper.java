package com.edrik.ecommerceDB.mapper;


import com.edrik.ecommerceDB.Dto.OrderDto;
import com.edrik.ecommerceDB.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderDto dto){
        Order order= new Order();
        order.setOrderedAt(dto.getOrderedAt());
        order.setStatus(dto.getStatus());
        order.setId(dto.getId());
        order.setPrice(dto.getPrice());
        order.setCreatedBy(dto.getCreatedBy());
        order.setOrderName(dto.getOrderName());
        return order;
    }
}
