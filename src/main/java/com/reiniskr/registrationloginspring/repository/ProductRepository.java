package com.reiniskr.registrationloginspring.repository;

import com.reiniskr.registrationloginspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Product findByName(String name);
    Product findByPrice(int price);
}



