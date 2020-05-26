package com.deemwar.products.api;


import com.deemwar.products.api.models.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> all() {

        return productService.findAll();
    }


    @GetMapping("/{id}")
    public Product getItemById(@PathVariable Long id)  {

        return productService.getProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"No Product Found for Id" + id) {
        });

    }

    @PostMapping
    public Product addItem(@RequestBody ProductRequest product) {
        try {
            log.info("product addItem ");
            Product result = productService.addProduct(product);

            log.info("product added " + result.toString());
            return result;
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id) {

        try {
            productService.removeProduct(id);
            return ResponseEntity.ok("Successfully Deleted");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

}
