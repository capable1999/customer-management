package com.ikmal.customer_management.controller;

import com.ikmal.customer_management.dto.CustomerDTO;
import com.ikmal.customer_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // List customers with pagination & search
    @GetMapping
    public String listCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "name") String sortBy,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(sortBy));
        Page<CustomerDTO> customerPage;

        if (keyword.isEmpty()) {
            customerPage = service.findPaginated(pageRequest);
        } else {
            customerPage = service.searchCustomers(keyword, pageRequest);
        }

        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customer", new CustomerDTO()); // new customer form
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "customers";
    }

    // Add or Update Customer
    @PostMapping
    public String saveOrUpdateCustomer(
            @Valid @ModelAttribute CustomerDTO customer,
            BindingResult result,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model,
            RedirectAttributes redirectAttrs
    ) {

        // If validation errors, show form again with all required model attributes
        if (result.hasErrors()) {
            Page<CustomerDTO> customerPage = service.findPaginated(PageRequest.of(page, 10));
            model.addAttribute("customerPage", customerPage);
            model.addAttribute("keyword", keyword);
            model.addAttribute("customer", customer); // keep user input
            return "customers";
        }

        // Update existing customer
        if (customer.getId() != null) {
            CustomerDTO existing = service.getCustomerById(customer.getId());
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setPhone(customer.getPhone());
            service.saveCustomer(existing);
            redirectAttrs.addFlashAttribute("successMessage", "Customer updated successfully!");
        } else {
            // Add new customer
            service.saveCustomer(customer);
            redirectAttrs.addFlashAttribute("successMessage", "Customer added successfully!");
        }

        return "redirect:/customers?keyword=" + keyword + "&page=" + page;
    }

    // Edit customer
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "") String keyword,
                               Model model) {
        Page<CustomerDTO> customerPage = service.findPaginated(PageRequest.of(page, 10));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customer", service.getCustomerById(id)); // pre-fill form
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "customers";
    }

    // Delete customer
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "") String keyword,
                                 RedirectAttributes redirectAttrs) {
        service.deleteCustomer(id);
        redirectAttrs.addFlashAttribute("successMessage", "Customer deleted successfully!");
        return "redirect:/customers?keyword=" + keyword + "&page=" + page;
    }
}
