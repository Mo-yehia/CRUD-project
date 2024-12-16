package com.POS.CRUD_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Data
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

}

