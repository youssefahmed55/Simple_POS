package com.example.simplepos.controller;


import com.example.simplepos.dao.SaleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(path = "/sales")
@RestController
public class SaleController {
    @Autowired
    private SaleDao saleDao;

    @GetMapping("/sales")
    public ResponseEntity<?> getAllSales() {
        return this.saleDao.getAllSales();
    }
}
