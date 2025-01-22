package com.example.simplepos.repo;

import com.example.simplepos.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
}
