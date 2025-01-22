package com.example.simplepos.dao;


import com.example.simplepos.controller.handler.CustomBadRequestException;
import com.example.simplepos.dto.OrderDto;
import com.example.simplepos.entity.OrderEntity;
import com.example.simplepos.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDao {

    @Autowired
    private OrderRepo orderRepo;


    public OrderEntity saveOrder(List<OrderDto> orderDto) {
        if (orderDto.isEmpty()) {
            return null;
        }
        Double total = 0.0;
        for (OrderDto order : orderDto) {
            total += order.getProductPrice();
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTotal(total);
        return this.orderRepo.save(orderEntity);
    }

    public List<OrderEntity> getAllOrders() {
        return this.orderRepo.findAll();
    }

    public OrderEntity getOrderById(Integer id) {
        Optional<OrderEntity> opt = this.orderRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new CustomBadRequestException("Not Found Order With This Id", HttpStatus.NOT_FOUND);
        }
    }


}
