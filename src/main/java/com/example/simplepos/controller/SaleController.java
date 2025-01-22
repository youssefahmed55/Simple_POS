package com.example.simplepos.controller;


import com.example.simplepos.dao.SaleDao;
import com.example.simplepos.entity.SaleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/sales")
@RestController
public class SaleController {
    @Autowired
    private SaleDao saleDao;

    @GetMapping("/sales")
    public List<SaleEntity> getAllSales() {
        return this.saleDao.getAllSales();
    }
}
