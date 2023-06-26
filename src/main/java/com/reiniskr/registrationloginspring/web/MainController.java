package com.reiniskr.registrationloginspring.web;

import com.reiniskr.registrationloginspring.model.Product;
import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.repository.UserRepository;
import com.reiniskr.registrationloginspring.service.ProductService;
import com.reiniskr.registrationloginspring.service.ProductServiceImpl;
import com.reiniskr.registrationloginspring.web.dto.ProductDto;
import com.reiniskr.registrationloginspring.web.dto.UserLoginDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
//@RestController
@Controller
public class MainController {

    boolean UserLoggedIn = false;

    private User currentUser;

    @Autowired
    ProductServiceImpl productServiceImpl;

    private ProductService productService;
    public MainController(ProductService productService){
        super();
        this.productService = productService;
    }

    @Autowired
    UserRepository userRepository;
    

    @ModelAttribute("user")
    public UserLoginDto userLoginDto(){
        return new UserLoginDto();
    }

    @GetMapping("/")
    public String home() {
        return "/login";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
    @PostMapping("/login")
    public String loginUserAccount(@ModelAttribute("user") UserLoginDto loginDto, HttpServletResponse response) {
        System.out.println("Entered email: "+loginDto.getEmail());
        System.out.println("Entered password: "+loginDto.getPassword());
        User existingUser = userRepository.findByEmail(loginDto.getEmail());

        if (existingUser != null){
            if (existingUser.getPassword().equals(loginDto.getPassword())) {
                UserLoggedIn = true;
                currentUser = existingUser;
                return "redirect:/dashboard";
            }
        }

        return "redirect:/login?error";

//        return "login";
//
//        return "redirect:/login?success";
    }

    @Autowired ProductRepository productRepository;
    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        if(currentUser == null)
            return "redirect:/login";
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user",currentUser);
        return "Dashboard";
    }

    @PostMapping("/dashboard")
    public String getFromDashboard(@ModelAttribute("product")ProductDto productDto){
        System.out.println(productDto);

        return "login";
    }


    @GetMapping("/adminDashboard")
    public String showAdminDashboard(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("newProd", new ProductDto());
        if (!currentUser.isAdmin())
            return "/login";
        return "AdminDashboard";

    }

    @PostMapping("/adminDashboard")
    public String loginUserAccount() {
//        System.out.println("Product name: "+ productDto.getName());
//        productRepository.save(productDto);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/editProd/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Optional<Product> product = productRepository.findById((long)id);
        Product test =  product.get();
        System.out.println("Optional produkts:"+ product);
        System.out.println("Produkts:"+ test);
        model.addAttribute("CurrentProd",test);
        return "editProductForm";
    }

    @PostMapping("/editProd/{id}")
    public String SubmitEdit(@ModelAttribute("CurrentProd")Product updatedProduct){
        Optional<Product> ExistingProductOptional = productRepository.findById(updatedProduct.getId());
        Product ExistingProduct =  ExistingProductOptional.get();

        ExistingProduct.setName(updatedProduct.getName());
        ExistingProduct.setPrice(updatedProduct.getPrice());
        ExistingProduct.setStoredAmount(updatedProduct.getStoredAmount());

        productRepository.save(ExistingProduct);

//        System.out.println("Updated product name:" + product.getName());
        return "redirect:/dashboard";

    }

    @GetMapping("deleteProd/{id}")
    public String delete(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/adminDashboard";


    }
    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model){
        model.addAttribute("newProd", new ProductDto());
        return "addNewProduct";
    }
    @PostMapping("/addNewProduct")
    public String addnewProductSubmit(@ModelAttribute("newProd")ProductDto productDto){
        productService.save(productDto);
        return "redirect:/adminDashboard";
    }


    // ------------- API interface ---------------//
    // delete endpoint is the same as the UI one







}
/*
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }*/