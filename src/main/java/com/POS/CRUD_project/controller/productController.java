package com.POS.CRUD_project.controller;

import com.POS.CRUD_project.entity.productEntity;
import com.POS.CRUD_project.service.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private productService productService;


    @GetMapping
    public ResponseEntity<Page<productEntity>> getAllProducts(Pageable pageable) {
        Page<productEntity> products = productService.getAllProducts(pageable);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<productEntity> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<productEntity> getProductByBarcode(@PathVariable String barcode) {
        return productService.findByBarcode(barcode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity<productEntity> saveProduct(@Valid @RequestBody productEntity orderEntity) {
        productEntity savedProduct = productService.saveProduct(orderEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }


    @PutMapping("id/{id}")
    public ResponseEntity<productEntity> updateProductById(
            @PathVariable Long id,
            @Valid @RequestBody productEntity updatedProduct) {
        try {
            productEntity updated = productService.updateProductById(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/add-list")
    public List<productEntity> saveListProducts(@RequestBody List<productEntity> productEntities) {

        return productService.saveListProducts(productEntities);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<String> deleteListProducts(@RequestBody List<Long> productIds) {
        productService.deleteListProductsByIds(productIds);
        return ResponseEntity.ok("The list of products is deleted successfully.");
    }


    @DeleteMapping("delete-all")
    public ResponseEntity<String>deleteAll(){
        productService.deleteAll();
        return ResponseEntity.ok("All products is deleted successfully.");
    }

}