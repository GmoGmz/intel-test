package com.intel.exceptions;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
