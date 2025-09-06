package com.example.simplepos.dao;


import com.example.simplepos.dto.OrderDto;
import com.example.simplepos.entity.ApiResponse;
import com.example.simplepos.entity.OrderEntity;
import com.example.simplepos.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDao {

    @Autowired
    private OrderRepo orderRepo;

    private ProductDao productDao;

    public OrderDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ResponseEntity<?> saveOrder(List<OrderDto> orderDto) {
        if (orderDto == null || orderDto.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Order cannot be empty", null));
        }

        for (OrderDto dto : orderDto) {
            if (!this.productDao.existsById(dto.getProductId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false,
                                "Product not found with ID: " + dto.getProductId(),
                                null));
            }
        }

        double total = orderDto.stream()
                .mapToDouble(OrderDto::getProductPrice)
                .sum();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTotal(total);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Order Saved Successfully", this.orderRepo.save(orderEntity))
        );
    }

    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "", this.orderRepo.findAll())
        );
    }

    public ResponseEntity<?> getOrderById(Integer id) {
        return this.orderRepo.findById(id)
                .<ResponseEntity<?>>map(product ->
                        ResponseEntity.ok(
                                new ApiResponse<>(true, "Order found", product)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Order not found", null))
                );
    }


}
