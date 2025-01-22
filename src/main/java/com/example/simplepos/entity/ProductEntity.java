package com.example.simplepos.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "products")
@Data
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "barcode", unique = true)
    private String barcode;


    @JsonManagedReference
    @OneToMany(mappedBy = "productEntity")
    private List<SaleEntity> saleEntity;
}
