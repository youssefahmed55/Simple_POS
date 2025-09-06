package com.example.simplepos.dao;


import com.example.simplepos.entity.ApiResponse;
import com.example.simplepos.entity.ProductEntity;
import com.example.simplepos.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductDao {

    @Autowired
    private ProductRepo productRepo;

    public boolean existsById(Integer id) {
        return this.productRepo.existsById(id);
    }

    public ResponseEntity<?> addProduct(ProductEntity product) {
        if (this.productRepo.existsByBarcode(product.getBarcode())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false,
                            "Barcode already exists: " + product.getBarcode(),
                            null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product Added Successfully", this.productRepo.save(product))
        );


    }

    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "", this.productRepo.findAll())
        );
    }

    public ResponseEntity<?> updateProduct(ProductEntity product) {

        return this.productRepo.findById(product.getProductId())
                .<ResponseEntity<?>>map(existing -> {
                    // update fields (you can add more if needed)
                    if (this.productRepo.existsByBarcode(product.getBarcode())) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse<>(false,
                                        "Barcode already exists: " + product.getBarcode(),
                                        null));
                    }
                    existing.setProductName(product.getProductName());
                    existing.setProductPrice(product.getProductPrice());
                    existing.setBarcode(product.getBarcode());

                    ProductEntity updated = this.productRepo.save(existing);

                    return ResponseEntity.ok(
                            new ApiResponse<>(true, "Product updated successfully", updated)
                    );
                })
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Product not found", null))
                );
    }

    public ResponseEntity<?> getProductById(Integer id) {
        return this.productRepo.findById(id)
                .<ResponseEntity<?>>map(product ->
                        ResponseEntity.ok(
                                new ApiResponse<>(true, "Product found", product)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Product not found", null))
                );
    }

    public ResponseEntity<?> getProductByBarcode(String barcode) {
        return this.productRepo.findByBarcode(barcode)
                .<ResponseEntity<?>>map(product ->
                        ResponseEntity.ok(
                                new ApiResponse<>(true, "Product found", product)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Product not found with this barcode", null))
                );
    }


    public ResponseEntity<?> deleteProductById(Integer id) {
        return this.productRepo.findById(id)
                .<ResponseEntity<?>>map(product -> {
                    this.productRepo.delete(product);
                    return ResponseEntity.ok(
                            new ApiResponse<>(true, "Product deleted successfully", null)
                    );
                })
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Product not found", null))
                );
    }
}
