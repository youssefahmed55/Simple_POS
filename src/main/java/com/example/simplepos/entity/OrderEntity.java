package com.example.simplepos.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "orders")
@Data
@Entity
public class OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "total")
    private Double total;

    @JsonManagedReference
    @OneToMany(mappedBy = "orderEntity")
    private List<SaleEntity> saleEntity;
}
