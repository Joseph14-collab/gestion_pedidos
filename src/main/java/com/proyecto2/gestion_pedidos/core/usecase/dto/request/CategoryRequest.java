package com.proyecto2.gestion_pedidos.core.usecase.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String description;
}
