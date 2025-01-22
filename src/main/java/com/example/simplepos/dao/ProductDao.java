package com.example.simplepos.dao;


import com.example.simplepos.controller.handler.CustomBadRequestException;
import com.example.simplepos.entity.ModelResponse;
import com.example.simplepos.entity.ProductEntity;
import com.example.simplepos.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDao {

    @Autowired
    private ProductRepo productRepo;

    public ModelResponse addProduct(ProductEntity product) {
        this.productRepo.save(product);
        return new ModelResponse(1, "Added Successfully");
    }

    public List<ProductEntity> getAllProducts() {
        return this.productRepo.findAll();
    }

    public ModelResponse updateProduct(ProductEntity product) {
        Optional<ProductEntity> opt = this.productRepo.findById(product.getProductId());
        if (opt.isPresent()) {
            this.productRepo.save(product);
            return new ModelResponse(1, "Updated Successfully");
        } else {
            throw new CustomBadRequestException("Updated UnSuccessfully, Not Found Product With This Id", HttpStatus.NOT_FOUND);
        }
    }

    public ProductEntity getProductById(Integer id) {
        Optional<ProductEntity> opt = this.productRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new CustomBadRequestException("Not Found Product With This Id", HttpStatus.NOT_FOUND);
        }
    }

    public ProductEntity getProductByBarcode(String barcode) {
        Optional<ProductEntity> opt = Optional.ofNullable(this.productRepo.findByBarcode(barcode));
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new CustomBadRequestException("Not Found Product With This Barcode", HttpStatus.NOT_FOUND);
        }
    }


    public ModelResponse deleteProductById(Integer id) {
        Optional<ProductEntity> opt = this.productRepo.findById(id);
        if (opt.isPresent()) {
            this.productRepo.deleteById(id);
            return new ModelResponse(1, "Deleted Successfully");
        } else {
            throw new CustomBadRequestException("Deleted UnSuccessfully", HttpStatus.NOT_FOUND);
        }
    }
}
