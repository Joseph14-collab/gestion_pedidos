package com.proyecto2.gestion_pedidos.core.usecase.port.in.customer;

import com.proyecto2.gestion_pedidos.core.entity.Customer;

public interface GetCustomerCase {
    Customer getCustomer(Long id);
}
