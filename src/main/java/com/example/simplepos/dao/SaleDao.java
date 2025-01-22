package com.example.simplepos.dao;


import com.example.simplepos.dto.OrderDto;
import com.example.simplepos.entity.OrderEntity;
import com.example.simplepos.entity.SaleEntity;
import com.example.simplepos.repo.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public OrderEntity saveSale(List<OrderDto> orderDto) {
        if (orderDto.isEmpty()) {
            return null;
        }
        OrderEntity order = this.orderDao.saveOrder(orderDto);
        orderDto.forEach(o -> {
            SaleEntity saleEntity = new SaleEntity();
            saleEntity.setOrderId(order.getOrderId());
            saleEntity.setProductId(o.getProductId());
            this.saleRepo.save(saleEntity);
        });
        return order;
    }

    public List<SaleEntity> getAllSales() {
        return this.saleRepo.findAll();
    }


}
