package com.codewithmosh.store.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private byte categoryId;
}
