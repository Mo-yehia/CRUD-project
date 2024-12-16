package com.POS.CRUD_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
public class productEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double productPrice;

    @Pattern(regexp = "\\d+", message = "Barcode must contain only digits")
    private String productBarcode;

    public @NotBlank(message = "Product name is required") String getProductName() {
        return productName;
    }

    public void setProductName(@NotBlank(message = "Product name is required") String productName) {
        this.productName = productName;
    }

    public @NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero") Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(@NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero") Double productPrice) {
        this.productPrice = productPrice;
    }

    public @Pattern(regexp = "\\d+", message = "Barcode must contain only digits") String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(@Pattern(regexp = "\\d+", message = "Barcode must contain only digits") String productBarcode) {
        this.productBarcode = productBarcode;
    }
}

