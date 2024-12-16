package com.POS.CRUD_project.service;

import com.POS.CRUD_project.repositroy.productRepo;
import com.POS.CRUD_project.entity.productEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class productService {
    @Autowired
    private productRepo productRepo;


    public Page<productEntity> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public Optional<productEntity> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Optional<productEntity> findByBarcode(String ProductBarcode) {
        return productRepo.findByProductBarcode(ProductBarcode);
    }


    public productEntity saveProduct(productEntity orderEntity) {

        return productRepo.save(orderEntity);
    }

    public productEntity updateProductById(Long productId, @org.jetbrains.annotations.NotNull productEntity updatedProduct) {
        // Fetch the existing product by ID
        productEntity existingProduct = productRepo.findById(productId)
                .orElseThrow(() -> {
                    return new RuntimeException("Product with ID " + productId + " not found");
                });

        // Update the fields of the existing product with values from the updated product
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductPrice(updatedProduct.getProductPrice());
        existingProduct.setProductBarcode(updatedProduct.getProductBarcode());

        // Save and return the updated product
        return productRepo.save(existingProduct);
    }

    public List<productEntity> saveListProducts(List<productEntity> productEntities) {

        return productRepo.saveAll(productEntities);
    }

    public boolean deleteProductById(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public void deleteAll(){
        productRepo. deleteAll();
    }

    public void deleteListProductsByIds(List<Long> productIds) {
        List<productEntity> productsToDelete = productRepo.findAllById(productIds);
        if (productsToDelete.size() != productIds.size()) {
            throw new RuntimeException("Some products could not be found for deletion");
        }

        // Delete the products
        productRepo.deleteAll(productsToDelete);
    }
}
