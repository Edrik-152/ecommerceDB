package com.edrik.ecommerceDB.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String orderName;
    private Long price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant orderedAt;
    private String createdBy;
}
