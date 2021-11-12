package com.intel.storage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.intel.beans.Customer;

/**
 * This is the data storage layer, on which we could implement
 * any kind of interactions with a DB or similar
 *
 * So far, we're designing a Singleton type of class to mimic that we have
 * only one database
 */
@Component
public class ObjectStorage {
    private static Map<Long, Customer> dbInstance;

    // Static method to create instance of Singleton class
    public static Map<Long, Customer> getInstance() {
        if (dbInstance == null) {
            dbInstance = new HashMap<>();
        }
        return dbInstance;
    }
}
