package com.reiniskr.registrationloginspring.repository;

import com.reiniskr.registrationloginspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Modifying
//    @Query("delete from Product p where p.id = ?1")
//    void deleteProductsById(Long id);



    @Modifying
    @Query("update Product set id=?2 where id = ?1")
    void updateProductIdById(Long currentId, Long newId);

    Product deleteProductById(Long id);

    List<Product> findAll();
    Product findByName(String name);
    Product findByPrice(int price);
    Optional<Product> findById(Long id);
}



