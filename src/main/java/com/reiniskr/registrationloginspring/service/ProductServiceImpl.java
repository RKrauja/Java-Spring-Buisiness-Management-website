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

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;


//    @Override
//    public Product update(Long id, Product product) {
//
//
//    }

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


//@Override
//public User save(UserRegistrationDto registrationDto) {
//    User user = new User(registrationDto.getFirstName(),
//            registrationDto.getLastName(), registrationDto.getEmail(),
//           passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
//
//    return userRepository.save(user);
//}

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if(user == null)
//            throw new UsernameNotFoundException("Invalid username or password");
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),null/*,mapRolesToAuthorities(user.getRoles())*/);
//    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//
//    }

}
