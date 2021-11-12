package com.intel.handlers;

import java.util.Map;

import com.intel.exceptions.CustomerNotFoundException;
import com.intel.beans.Customer;
import com.intel.storage.ObjectStorage;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerHandler {
    private static long customerNumber = 0;

    public long createCustomer(String name) {
        ObjectStorage.getInstance().put(customerNumber, new Customer(name));
        return customerNumber++;
    }

    public String readCustomer(Long id) {
        if (ObjectStorage.getInstance().containsKey(id)) {
            Customer customer = ObjectStorage.getInstance().get(id);
            return "The customer "
                    + customer.getName() + " has made "
                    + customer.getNumberOfPurchases() + " purchases.";
        }
        return "There is no record of the customer.";
    }

    public Customer getCustomer(Long id) throws CustomerNotFoundException {
        if (ObjectStorage.getInstance().containsKey(id)) {
            return ObjectStorage.getInstance().get(id);
        }

        throw new CustomerNotFoundException("The customer was not found in the database");
    }

    public void updateCustomer(Long id, Customer customer) {
        if (ObjectStorage.getInstance().containsKey(id)) {
            ObjectStorage.getInstance().put(id, customer);
        }
    }

    public boolean deleteCustomer(Long id) {
        if (ObjectStorage.getInstance().containsKey(id)) {
            ObjectStorage.getInstance().remove(id);
            return true;
        }
        return false;
    }

    public String listCustomers() {
        String response = "CUSTOMER ID - CUSTOMER NAME";
        for (Map.Entry<Long, Customer> entry : ObjectStorage.getInstance().entrySet()) {
            response += entry.getKey() + " - " + entry.getValue().getName() + ", ";
        }
        return response;
    }
}
