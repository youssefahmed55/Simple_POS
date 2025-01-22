package com.example.simplepos.controller;

import com.example.simplepos.dao.OrderDao;
import com.example.simplepos.dao.SaleDao;
import com.example.simplepos.dto.OrderDto;
import com.example.simplepos.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SaleDao saleDao;

    @PostMapping(path = "/save-order")
    public OrderEntity saveOrder(@RequestBody List<OrderDto> order) {
        return this.saleDao.saveSale(order);
    }

    @GetMapping(path = "/orders")
    public List<OrderEntity> getAllOrders() {
        return this.orderDao.getAllOrders();
    }

    @GetMapping(path = "/order-by-id")
    public OrderEntity getOrderById(@RequestParam Integer id) {
        return this.orderDao.getOrderById(id);
    }


}
