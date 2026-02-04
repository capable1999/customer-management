package com.ikmal.customer_management.controller;

import com.ikmal.customer_management.entity.Customer;
import com.ikmal.customer_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Display page
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    // Add new customer
    @PostMapping
    public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", service.getAllCustomers());
            return "customers";
        }
        service.saveCustomer(customer);
        return "redirect:/customers";
    }

    // Edit customer
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getCustomerById(id));
        model.addAttribute("customers", service.getAllCustomers());
        return "customers";
    }

    // Delete customer
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}
