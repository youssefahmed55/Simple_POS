package com.example.simplepos.controller;

import com.example.simplepos.dao.OrderDao;
import com.example.simplepos.dao.SaleDao;
import com.example.simplepos.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveOrder(@RequestBody List<OrderDto> order) {
        return this.saleDao.saveSale(order);
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<?> getAllOrders() {
        return this.orderDao.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        return this.orderDao.getOrderById(id);
    }


}
