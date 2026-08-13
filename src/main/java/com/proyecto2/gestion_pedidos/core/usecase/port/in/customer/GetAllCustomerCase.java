package com.proyecto2.gestion_pedidos.core.usecase.port.in.customer;

import com.proyecto2.gestion_pedidos.core.entity.Customer;

import java.util.List;

public interface GetAllCustomerCase {
    List<Customer> getAllCustomers();
    List<Customer> getActiveCustomers();
    List<Customer> getInactiveCustomers();
}
