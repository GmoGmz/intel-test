package com.intel.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Liveness {
    @GetMapping("/liveness")
    public String liveness() {
        return "API is alive!!!";
    }

}
