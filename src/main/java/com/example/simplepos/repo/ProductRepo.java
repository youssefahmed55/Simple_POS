package com.example.simplepos.repo;

import com.example.simplepos.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByBarcode(String barcode);

    boolean existsByBarcode(String barcode);
}
