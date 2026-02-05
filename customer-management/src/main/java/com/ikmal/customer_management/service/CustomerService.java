package com.ikmal.customer_management.service;

import com.ikmal.customer_management.entity.Customer;
import com.ikmal.customer_management.dto.CustomerDTO;
import com.ikmal.customer_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // Entity <-> DTO conversion
    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        return customer;
    }

    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll()
                         .stream()
                         .map(this::toDTO)
                         .collect(Collectors.toList());
    }

    public CustomerDTO saveCustomer(CustomerDTO dto) {
        Customer customer = toEntity(dto);
        Customer saved = repository.save(customer);
        return toDTO(saved);
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        return toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    public Page<CustomerDTO> findPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                         .map(this::toDTO);
    }

    public Page<CustomerDTO> searchCustomers(String keyword, Pageable pageable) {
        return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable)
                         .map(this::toDTO);
    }
}
