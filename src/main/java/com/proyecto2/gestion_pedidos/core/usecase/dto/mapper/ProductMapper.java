package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Product;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.ProductRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toDomain(ProductRequest request){
        return Product.builder()
                .categoryId(request.getCategoryId())
                .sku(request.getSku())
                .name(request.getName())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity()).build();
    }
    public ProductResponse toResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .sku(product.getSku())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .active(product.getActive()).build();
    }
}
