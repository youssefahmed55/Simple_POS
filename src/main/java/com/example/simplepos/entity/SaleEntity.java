package com.example.simplepos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "sales")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)  // Enable auditing for this entity
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "order_id")
    private Integer orderId;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now(); // Default value if not already set
        }
    }


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity orderEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity productEntity;
}
