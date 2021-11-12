package com.intel.controller;

import com.intel.controllers.CustomerController;
import com.intel.handlers.CustomerHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    CustomerHandler customerHandler;

    @InjectMocks
    CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void customerEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from the Customer Controller")));
    }

    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/createCustomer/Guillermo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Customer Created with ID-")));
    }

    @Test
    public void readCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/createCustomer/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
