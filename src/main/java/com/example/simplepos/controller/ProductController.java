package com.example.simplepos.controller;


import com.example.simplepos.dao.ProductDao;
import com.example.simplepos.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/products")
@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductEntity product) {
        return this.productDao.addProduct(product);
    }

    @GetMapping("/get-products")
    public ResponseEntity<?> getAllProducts() {
        return this.productDao.getAllProducts();
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity product) {
        return this.productDao.updateProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return this.productDao.getProductById(id);
    }

    @GetMapping("/get-product-by-barcode")
    public ResponseEntity<?> getProductByBarcode(@RequestParam String barcode) {
        return this.productDao.getProductByBarcode(barcode);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<?> deleteProductById(@RequestParam Integer id) {
        return this.productDao.deleteProductById(id);
    }
}
