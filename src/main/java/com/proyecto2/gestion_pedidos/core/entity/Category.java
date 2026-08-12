package com.proyecto2.gestion_pedidos.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Category {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
}
