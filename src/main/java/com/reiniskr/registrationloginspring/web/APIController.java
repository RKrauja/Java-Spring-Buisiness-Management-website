package com.reiniskr.registrationloginspring.web;


import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    ProductRepository productRepository;


    @GetMapping("/Product")
    private List<Product> getAllProducts(){
        List<Product> list = productServiceImpl.getAllProducts();
        return list;
//        return productRepository.findAll();
    }

    @GetMapping("/Product/{id}")
    private Product getProduct(@PathVariable("id")Long id){
        return productRepository.findById(id).get();
    }

    @DeleteMapping("/Product/{id}")
    private String deleteProduct(@PathVariable("id")Long id){
        if (!currentUser.isAdmin())
            return "Error: Current user is either not logged in or is not admin";
        productRepository.deleteById(id);
        return "Successfully deleted user with id: " + id;
    }

    @PostMapping("/Product")
    private String saveProduct(@RequestBody Product product){
        if (!currentUser.isAdmin())
            return "Error: Current user is either not logged in or is not admin";
        productRepository.save(product);
        return "Inserted product with id:" + product.getId();
    }

    @PutMapping("/Product")
    private String update(@RequestBody Product product){
        if (!currentUser.isAdmin())
            return "Error: Current user is either not logged in or is not admin";
        Product ExistingProduct = productRepository.findById(product.getId()).get();

        ExistingProduct.setName(product.getName());
        ExistingProduct.setPrice(product.getPrice());
        ExistingProduct.setStoredAmount(product.getStoredAmount());

        productRepository.save(ExistingProduct);

        return "Product updated";
    }
}
