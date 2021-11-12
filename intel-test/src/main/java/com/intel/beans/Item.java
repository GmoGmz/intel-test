package com.intel.beans;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Getter @Setter private String name;
    @Getter @Setter private int price;
}
