package com.intel.handlers;

import com.intel.beans.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseHandlerTest {
    @Autowired
    CustomerHandler customerHandler;

    @Autowired
    PurchaseHandler purchaseHandler;

    private List<Item> items = new ArrayList<>();
    private long id;

    @BeforeAll
    void setup() {
        id = customerHandler.createCustomer(any(String.class));
        items.addAll(Arrays.asList(
                new Item(any(String.class), 1),
                new Item(any(String.class), 1),
                new Item(any(String.class), 1)
        ));
    }

    @Test
    public void purchaseMade() {
        double total = purchaseHandler.purchaseMade(id, items);
        Assertions.assertThat(total).isEqualTo(3);
        Assertions.assertThat(customerHandler.getCustomer(id).getNumberOfPurchases()).isEqualTo(1);
        total = purchaseHandler.purchaseMade(id, items);
        Assertions.assertThat(total).isEqualTo(3 * 0.99);
        Assertions.assertThat(customerHandler.getCustomer(id).getNumberOfPurchases()).isEqualTo(2);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        total = purchaseHandler.purchaseMade(id, items);
        Assertions.assertThat(total).isEqualTo(3 * 0.95);
        Assertions.assertThat(customerHandler.getCustomer(id).getNumberOfPurchases()).isEqualTo(7);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        purchaseHandler.purchaseMade(id, items);
        total = purchaseHandler.purchaseMade(id, items);
        Assertions.assertThat(total).isEqualTo(3 * 0.9);
        Assertions.assertThat(customerHandler.getCustomer(id).getNumberOfPurchases()).isEqualTo(12);
    }
}
