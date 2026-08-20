package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Product;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.product.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.product.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements RegisterProductCase, GetProductCase, GetAllProductCase, UpdateProductCase, DeleteProductCase, RestoreProductCase {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product registerProduct(Product request){
        Product newProduct = Product.builder()
                .categoryId(request.getCategoryId())
                .sku(request.getSku())
                .name(request.getName())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .active(true).build();
        return productRepositoryPort.save(newProduct);
    }

    @Override
    public Product getProduct(Long id){
        return productRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepositoryPort.findAll();
    }

    @Override
    public List<Product> getActiveProducts(){
        return productRepositoryPort.findByActive(true);
    }

    @Override
    public List<Product> getInactiveProducts(){
        return productRepositoryPort.findByActive(false);
    }

    @Override
    public Product updateProduct(Long id, Product product){
        Product existingProduct = getProduct(id);
        Product updatedProduct = Product.builder()
                .id(existingProduct.getId())
                .categoryId(product.getCategoryId())
                .sku(product.getSku())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .active(existingProduct.getActive()).build();
        return productRepositoryPort.update(updatedProduct);
    }

    @Override
    public boolean deleteProduct(Long id){
        Product existingProduct = getProduct(id);
        Product deletedProduct = Product.builder()
                .id(existingProduct.getId())
                .categoryId(existingProduct.getCategoryId())
                .sku(existingProduct.getSku())
                .name(existingProduct.getName())
                .price(existingProduct.getPrice())
                .stockQuantity(existingProduct.getStockQuantity())
                .active(false).build();
        productRepositoryPort.delete(deletedProduct);
        return true;
    }

    @Override
    public boolean restoreProduct(Long id){
        Product existingProduct = getProduct(id);
        Product updatedProduct = Product.builder()
                .id(existingProduct.getId())
                .categoryId(existingProduct.getCategoryId())
                .sku(existingProduct.getSku())
                .name(existingProduct.getName())
                .price(existingProduct.getPrice())
                .stockQuantity(existingProduct.getStockQuantity())
                .active(true).build();
        productRepositoryPort.update(updatedProduct);
        return true;
    }
}


