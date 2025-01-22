package com.example.simplepos.repo;

import com.example.simplepos.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepo extends JpaRepository<SaleEntity, Integer> {
}
