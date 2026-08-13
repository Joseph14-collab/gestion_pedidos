package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Customer;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.CustomerRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toDomain(CustomerRequest request){
        return Customer.builder()
                .documentNumber(request.getDocumentNumber())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber()).build();
    }
    public CustomerResponse toResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .documentNumber(customer.getDocumentNumber())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .active(customer.getActive()).build();
    }
}
