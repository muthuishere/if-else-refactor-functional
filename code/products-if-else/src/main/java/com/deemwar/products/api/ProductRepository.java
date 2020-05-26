package com.deemwar.products.api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product,Long> {


	Optional<Product> findById(Long id);



	void deleteById(Long id);


}
