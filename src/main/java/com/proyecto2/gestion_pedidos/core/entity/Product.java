package com.proyecto2.gestion_pedidos.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private Long categoryId;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean active;
}
