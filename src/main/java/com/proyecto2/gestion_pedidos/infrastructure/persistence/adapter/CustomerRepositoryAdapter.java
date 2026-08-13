package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Customer;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.customer.CustomerRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.CustomerEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer){
        CustomerEntity entity = toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Customer> findByActive(boolean active){
        return customerRepository.findByActive(active).stream().map(this::toDomain).toList();
    }

    @Override
    public Customer update(Customer customer){
        CustomerEntity entity = toEntity(customer);
        CustomerEntity updated = customerRepository.save(entity);
        return toDomain(updated);
    }

    @Override
    public Customer delete(Customer customer){
        CustomerEntity entity = toEntity(customer);
        CustomerEntity deleted = customerRepository.save(entity);
        return toDomain(deleted);
    }


    //
    private CustomerEntity toEntity(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setDocumentNumber(customer.getDocumentNumber());
        entity.setFullName(customer.getFullName());
        entity.setEmail(customer.getEmail());
        entity.setPhoneNumber(customer.getPhoneNumber());
        entity.setActive(customer.getActive());
        return entity;
    }
    private Customer toDomain(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .documentNumber(entity.getDocumentNumber())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .active(entity.getActive()).build();
    }
}
