package com.kodilla.ecommercee.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {

    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal price;
}
