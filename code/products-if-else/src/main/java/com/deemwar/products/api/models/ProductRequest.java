package com.deemwar.products.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequest {

   private String name;
    private String category;
    private double price;


}
