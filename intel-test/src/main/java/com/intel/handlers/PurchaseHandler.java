package com.intel.handlers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intel.beans.Customer;
import com.intel.beans.Purchase;
import com.intel.beans.Item;
import com.intel.exceptions.CustomerNotFoundException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PurchaseHandler {
    private static final ZoneId zonedId = ZoneId.of("America/Chicago");

    @Autowired
    CustomerHandler customerHandler;

    public double purchaseMade(Long customerId, List<Item> items) {
        try {
            Customer customer = customerHandler.getCustomer(customerId);
            double total = calculateAmount(customer.getNumberOfPurchases(), items);
            customer.addPurchase(new Purchase(LocalDate.now(zonedId), total, items));

            return total;
        } catch (CustomerNotFoundException cnfe) {
            log.error("Up to this point, the customer should've be logged in");
            return -1;
        }
    }

    private double calculateAmount(int numberOfPurchases, List<Item> items) {
        double subTotal =  items.stream()
                .mapToInt(Item::getPrice)
                .sum();
        double total = 0;

        if (numberOfPurchases >= 1 && numberOfPurchases <= 2) {
            total = subTotal * 0.99;
            log.info("A discount of 1% was applied to the purchase with a subtotal of {}", subTotal);
        } else if (numberOfPurchases >= 2 && numberOfPurchases <= 5) {
            total = subTotal * 0.98;
            log.info("A discount of 2% was applied to the purchase with a subtotal of {}", subTotal);
        } else if (numberOfPurchases >= 5 && numberOfPurchases <= 10) {
            total = subTotal * 0.95;
            log.info("A discount of 5% was applied to the purchase with a subtotal of {}", subTotal);
        } else if (numberOfPurchases >= 10) {
            total = subTotal * 0.9;
            log.info("A discount of 10% was applied to the purchase with a subtotal of {}", subTotal);
        } else {
            total = subTotal;
        }

        return total;
    }
}
