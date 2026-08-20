package com.proyecto2.gestion_pedidos.core.usecase.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private Long categoryId;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean active;
}
