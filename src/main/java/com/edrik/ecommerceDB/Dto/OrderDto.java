package com.edrik.ecommerceDB.Dto;


import com.edrik.ecommerceDB.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private UUID id;
    private String orderName;
    private Long price;
    private OrderStatus status;
    private Instant orderedAt;
    private String createdBy;
}
