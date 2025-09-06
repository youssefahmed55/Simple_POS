package com.example.simplepos.dao;


import com.example.simplepos.dto.OrderDto;
import com.example.simplepos.entity.ApiResponse;
import com.example.simplepos.entity.OrderEntity;
import com.example.simplepos.entity.SaleEntity;
import com.example.simplepos.repo.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDao {

    @Autowired
    private SaleRepo saleRepo;

    private OrderDao orderDao;

    public SaleDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public ResponseEntity<?> saveSale(List<OrderDto> orderDto) {
        if (orderDto == null || orderDto.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Sale cannot be empty", null));
        }

        ResponseEntity<?> orderResponse = this.orderDao.saveOrder(orderDto);
        ApiResponse<?> apiResponse = (ApiResponse<?>) orderResponse.getBody();

        if (apiResponse == null || !apiResponse.isSuccess() || apiResponse.getData() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Order could not be created", null));
        }

        OrderEntity order = (OrderEntity) apiResponse.getData();

        orderDto.forEach(o -> {
            SaleEntity saleEntity = new SaleEntity();
            saleEntity.setOrderId(order.getOrderId());
            saleEntity.setProductId(o.getProductId());
            this.saleRepo.save(saleEntity);
        });

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Sale saved successfully", order)
        );
    }

    public ResponseEntity<?> getAllSales() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "", this.saleRepo.findAll())
        );
    }


}
