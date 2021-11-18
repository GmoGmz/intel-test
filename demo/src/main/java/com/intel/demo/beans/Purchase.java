package com.intel.demo.beans;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Getter private LocalDate date;
    @Getter @Setter private double amount;
    @Getter @Setter private List<Item> items;
}
