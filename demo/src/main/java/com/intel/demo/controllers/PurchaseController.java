package com.intel.demo.controllers;

import java.util.List;

import com.intel.demo.beans.Item;
import com.intel.demo.handlers.PurchaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shopping")
public class PurchaseController {
    @Autowired
    PurchaseHandler purchaseHandler;

    @GetMapping("/purchase")
    public ResponseEntity<String> helloPurchase() {
        return new ResponseEntity<>("Hello from the purchase controller", HttpStatus.OK);
    }

    @PostMapping("/purchaseMade/{id}")
    public ResponseEntity<String> purchaseMade(
            @PathVariable(value = "id") String customerId,
            @RequestBody List<Item> items
    ) {
        double amount = purchaseHandler.purchaseMade(Long.valueOf(customerId), items);
        return new ResponseEntity<>("The total for this purchase was: USD $" + amount, HttpStatus.OK);
    }
}
