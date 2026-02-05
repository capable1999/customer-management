package com.ikmal.customer_management.service;

import com.ikmal.customer_management.entity.Customer;
import com.ikmal.customer_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    public Page<Customer> findPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Customer> searchCustomers(String keyword, Pageable pageable) {
        return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
    }
}
