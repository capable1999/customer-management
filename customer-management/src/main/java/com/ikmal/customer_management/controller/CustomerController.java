package com.ikmal.customer_management.controller;

import com.ikmal.customer_management.entity.Customer;
import com.ikmal.customer_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String listCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "name") String sortBy,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(sortBy));
        Page<Customer> customerPage;

        if (keyword.isEmpty()) {
            customerPage = service.findPaginated(pageRequest);
        } else {
            customerPage = service.searchCustomers(keyword, pageRequest);
        }

        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customer", new Customer());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "customers";
    }

    @PostMapping
    public String saveOrUpdateCustomer(
        @Valid @ModelAttribute Customer customer,
        BindingResult result,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "") String keyword,
        Model model) {

    if (result.hasErrors()) {
        Page<Customer> customerPage = service.findPaginated(PageRequest.of(page, 10));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("keyword", keyword);
        return "customers";
    }

    if (customer.getId() != null) {
        Customer existing = service.getCustomerById(customer.getId());
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        service.saveCustomer(existing);
    } else {
        service.saveCustomer(customer);
    }

    return "redirect:/customers?keyword=" + keyword + "&page=" + page;
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Page<Customer> customerPage = service.findPaginated(PageRequest.of(0, 10));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customer", service.getCustomerById(id));
        model.addAttribute("keyword", "");
        return "customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}
