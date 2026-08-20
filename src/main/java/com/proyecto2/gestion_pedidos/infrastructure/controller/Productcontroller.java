package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Product;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.ProductMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.ProductRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.ProductResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class Productcontroller {
    private final RegisterProductCase registerProductCase;
    private final GetProductCase getProductCase;
    private final GetAllProductCase getAllProductCase;
    private final UpdateProductCase updateProductCase;
    private final DeleteProductCase deleteProductCase;
    private final RestoreProductCase restoreProductCase;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        Product created = registerProductCase.registerProduct(productMapper.toDomain(request));
        return new ResponseEntity<>(productMapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        Product product = getProductCase.getProduct(id);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getActiveProducts(){
        List<ProductResponse> responses = getAllProductCase.getActiveProducts().stream()
                .map(productMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> responses = getAllProductCase.getAllProducts().stream()
                .map(productMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<ProductResponse>> getInactiveProducts(){
        List<ProductResponse> responses = getAllProductCase.getInactiveProducts().stream()
                .map(productMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        Product updated = updateProductCase.updateProduct(id, productMapper.toDomain(request));
        return ResponseEntity.ok(productMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id){
        deleteProductCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<ProductResponse> restoreProduct(@PathVariable Long id){
        restoreProductCase.restoreProduct(id);
        return ResponseEntity.ok().build();
    }
}
