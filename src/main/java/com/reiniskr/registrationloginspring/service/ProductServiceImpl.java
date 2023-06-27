package com.reiniskr.registrationloginspring.service;

//import com.reiniskr.registrationloginspring.model.Role;

import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        List<Product> clone = products;
        return products;
    }
    @Override
    public Product save(ProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getStoredAmount(),productDto.getPrice());

        return productRepository.save(product);
    }
    @Override
    public Product deleteProductById(Long id){
        return productRepository.deleteProductById(id);
    }


}
