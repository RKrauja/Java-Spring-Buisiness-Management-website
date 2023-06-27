package com.reiniskr.registrationloginspring.service;

import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.web.dto.ProductDto;


public interface ProductService{
    Product save(ProductDto productDto);

    Product deleteProductById(Long id);
}
