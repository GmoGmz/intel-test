package com.intel.demo.handlers;

import com.intel.demo.beans.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.assertj.core.api.Assertions;

import static org.mockito.Mockito.any;

@SpringBootTest
public class CustomerHandlerTest {
    @Autowired
    CustomerHandler customerHandler;

    @Test
    public void createCustomer() {
        Long id = customerHandler.createCustomer(any(String.class));
        Assertions.assertThat(id).isEqualTo(6L);
    }

    @Test
    public void readCustomer() {
        Long id = customerHandler.createCustomer(any(String.class));
        String readCustomer = customerHandler.readCustomer(id);
        Assertions.assertThat(readCustomer).isEqualTo("The customer null has made 0 purchases.");
    }

    @Test
    public void updateCustomer() {
        Long id = customerHandler.createCustomer(any(String.class));
        Customer customer = customerHandler.getCustomer(id);
        customerHandler.updateCustomer(id, any(Customer.class));
        Customer updatedCustomer = customerHandler.getCustomer(id);
        Assertions.assertThat(customer).isNotEqualTo(updatedCustomer);
    }

    @Test
    public void deleteCustomer() {
        Long id = customerHandler.createCustomer(any(String.class));
        Assertions.assertThat(
                customerHandler.readCustomer(id)).isEqualTo("The customer null has made 0 purchases.");
        Assertions.assertThat(customerHandler.deleteCustomer(id)).isTrue();
        Assertions.assertThat(
                customerHandler.readCustomer(id)).isEqualTo("There is no record of the customer.");
    }
}
