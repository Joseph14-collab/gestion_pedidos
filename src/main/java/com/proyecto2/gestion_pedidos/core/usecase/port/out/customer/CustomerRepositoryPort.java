package com.proyecto2.gestion_pedidos.core.usecase.port.out.customer;

import com.proyecto2.gestion_pedidos.core.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    List<Customer> findByActive(boolean active);
    Customer update(Customer customer);
    Customer delete(Customer customer);
}
