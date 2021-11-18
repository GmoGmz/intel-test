package com.intel.demo.beans;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Customer extends CustomerAbstractClass {

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        this.numberOfPurchases = numberOfPurchases + 1;
    }

}
