package com.intel.demo.controllers;

import com.intel.demo.handlers.CustomerHandler;
import com.intel.demo.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerHandler customerHandler;

    @GetMapping("/customer")
    public ResponseEntity<String> helloCustomer() {
        return new ResponseEntity<>("Hello from the Customer Controller", HttpStatus.OK);
    }

    @PostMapping("/customer/createCustomer/{name}")
    public ResponseEntity<String> createCustomer(@PathVariable(value = "name") String customerName) {
        long customerNumber = customerHandler.createCustomer(customerName);
        return new ResponseEntity<>("Customer Created with ID-" + customerNumber,  HttpStatus.OK);
    }

    @GetMapping("/customer/readCustomer/{id}")
    public ResponseEntity<String> readCustomer(@PathVariable(value = "id") String id) {
        String result = customerHandler.readCustomer(Long.valueOf(id));
        if (result.equalsIgnoreCase("There is no record of the customer.")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(customerHandler.readCustomer(Long.valueOf(id)), HttpStatus.OK);
        }
    }

    @PutMapping("/customer/updateCustomer/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable(value = "id") Long id, @RequestBody Customer customer) {
        customerHandler.updateCustomer(id, customer);
        return new ResponseEntity<>("CUSTOMER UPDATED", HttpStatus.OK);
    }

    @DeleteMapping("/customer/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") Long id) {
        boolean response = customerHandler.deleteCustomer(id);
        if (response) {
            return new ResponseEntity<>("CUSTOMER DELETED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("CUSTOMER IS NOT IN THE DATABASE", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/customer/listCustomers")
    public ResponseEntity<String> listCustomers() {
        String customers = customerHandler.listCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
