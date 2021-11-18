package com.intel.demo.beans;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public abstract class CustomerAbstractClass {
    @Getter @Setter String name;
    @Getter @Setter int numberOfPurchases;
    @Getter @Setter List<Purchase> purchases = new ArrayList<>();

    public abstract void addPurchase(Purchase purchase);
}
