package com.deemwar.products.api;

import java.util.List;
import java.util.Optional;


import com.deemwar.products.api.models.ProductRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    private static Logger logger = LoggerFactory.getLogger(ProductService.class);


    public List<Product> findAll() {

        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Long id) {

        return productRepository.findById(id);
    }



    public Product addProduct(ProductRequest productRequest) {

        Product product = new Product();


        product.setName(productRequest.getName());


        product.setCategory(productRequest.getCategory());

       product.setPrice(productRequest.getPrice());


        return saveProduct(product);

    }


    @Transactional
    public Product saveProduct( Product result) {


       Product savedProduct = productRepository.save(result);

        return savedProduct;
    }

    @Transactional
    public void removeProduct(Long itemId) {

        productRepository.deleteById(itemId);

    }




}
