package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Product;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.product.ProductRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.ProductEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product){
        ProductEntity entity = toEntity(product);
        ProductEntity saved = productRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id){
        return productRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll().stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Product> findByActive(boolean active){
        return productRepository.findByActive(active).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public Product update(Product product){
        ProductEntity entity = toEntity(product);
        ProductEntity updated = productRepository.save(entity);
        return toDomain(updated);
    }

    @Override
    public Product delete(Product product){
        ProductEntity entity = toEntity(product);
        ProductEntity deleted = productRepository.save(entity);
        return toDomain(deleted);
    }

    //
    private ProductEntity toEntity(Product product){
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setCategoryId(product.getCategoryId());
        entity.setSku(product.getSku());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());
        entity.setActive(product.getActive());
        return entity;
    }
    private Product toDomain(ProductEntity entity){
        return Product.builder()
                .id(entity.getId())
                .categoryId(entity.getCategoryId())
                .sku(entity.getSku())
                .name(entity.getName())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .active(entity.getActive()).build();
    }

}
