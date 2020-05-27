package com.deemwar.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;




@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Product {

    Long productId;




    private String name;


    private String category;


    private Double price;

    public Product(){

    }

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;

    }


}
