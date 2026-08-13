package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Category;
import com.proyecto2.gestion_pedidos.core.entity.Customer;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.customer.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.customer.CustomerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements RegisterCustomerCase, GetCustomerCase, GetAllCustomerCase, UpdateCustomerCase, DeleteCustomerCase, RestoreCustomerCase {
    private final CustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer registerCustomer(Customer request){
        Customer newCustomer = Customer.builder()
                .documentNumber(request.getDocumentNumber())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .active(true).build();
        return customerRepositoryPort.save(newCustomer);
    }

    @Override
    public Customer getCustomer(Long id){
        return customerRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepositoryPort.findAll();
    }

    @Override
    public List<Customer> getActiveCustomers(){
        return customerRepositoryPort.findByActive(true);
    }

    @Override
    public List<Customer> getInactiveCustomers(){
        return customerRepositoryPort.findByActive(false);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer){
        Customer existingCustomer = getCustomer(id);
        Customer updatedCustomer = Customer.builder()
                .id(existingCustomer.getId())
                .documentNumber(customer.getDocumentNumber())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .active(existingCustomer.getActive()).build();
        return customerRepositoryPort.update(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(Long id){
        Customer existingCustomer = getCustomer(id);
        Customer deletedCustomer = Customer.builder()
                .id(existingCustomer.getId())
                .documentNumber(existingCustomer.getDocumentNumber())
                .fullName(existingCustomer.getFullName())
                .email(existingCustomer.getEmail())
                .phoneNumber(existingCustomer.getPhoneNumber())
                .active(false).build();
        customerRepositoryPort.delete(deletedCustomer);
        return true;
    }

    @Override
    public boolean restoreCustomer(Long id){
        Customer existingCustomer = getCustomer(id);
        Customer deletedCustomer = Customer.builder()
                .id(existingCustomer.getId())
                .documentNumber(existingCustomer.getDocumentNumber())
                .fullName(existingCustomer.getFullName())
                .email(existingCustomer.getEmail())
                .phoneNumber(existingCustomer.getPhoneNumber())
                .active(true).build();
        customerRepositoryPort.update(deletedCustomer);
        return true;
    }
}
