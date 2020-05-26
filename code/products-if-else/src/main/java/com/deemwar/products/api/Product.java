package com.deemwar.products.api;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
