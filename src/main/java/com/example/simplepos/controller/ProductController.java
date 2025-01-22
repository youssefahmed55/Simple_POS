package com.example.simplepos.controller;


import com.example.simplepos.dao.ProductDao;
import com.example.simplepos.entity.ModelResponse;
import com.example.simplepos.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/products")
@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @PostMapping("/add-product")
    public ModelResponse addProduct(@RequestBody ProductEntity product) {
        return this.productDao.addProduct(product);
    }

    @GetMapping("/get-products")
    public List<ProductEntity> getAllProducts() {
        return this.productDao.getAllProducts();
    }

    @PutMapping("/update-product")
    public ModelResponse updateProduct(@RequestBody ProductEntity product) {
        return this.productDao.updateProduct(product);
    }

    @GetMapping("/get-product-by-id")
    public ProductEntity getProductById(@RequestParam Integer id) {
        return this.productDao.getProductById(id);
    }

    @GetMapping("/get-product-by-barcode")
    public ProductEntity getProductByBarcode(@RequestParam String barcode) {
        return this.productDao.getProductByBarcode(barcode);
    }

    @DeleteMapping("/delete-product")
    public ModelResponse deleteProductById(@RequestParam Integer id) {
        return this.productDao.deleteProductById(id);
    }
}
