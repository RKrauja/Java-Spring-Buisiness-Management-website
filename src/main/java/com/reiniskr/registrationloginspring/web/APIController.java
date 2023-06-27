package com.reiniskr.registrationloginspring.web;


import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.repository.UserRepository;
import com.reiniskr.registrationloginspring.service.ProductServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIController {
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    User currentUser = new User();

//    @CookieValue(value = "username",defaultValue = "atta")
//    String username;

    @PostMapping("/LoginUser")
    private String loginUser(@RequestBody User user, HttpServletResponse response){
//        Cookie usernameCookie = new Cookie("username",user.getEmail());
//        Cookie passwordCookie = new Cookie("password", user.getPassword());
//        response.addCookie(usernameCookie);
//        response.addCookie(passwordCookie);
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null){
            if(existingUser.getPassword().equals(user.getPassword())){
                currentUser = existingUser;
                return "login successful";
            }
        }
        return "username or password is incorrect";
    }


    @GetMapping("/Product")
    private List<Product> getAllProducts(){
        List<Product> list = productServiceImpl.getAllProducts();
        return list;
//        return productRepository.findAll();
    }

    @GetMapping("/Product/{id}")
    private Product getProduct(@PathVariable("id")Long id){
        Optional<Product> prod = productRepository.findById(id);
        if(prod.isPresent()) {
            return productRepository.findById(id).get();
        }
        return null;
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
