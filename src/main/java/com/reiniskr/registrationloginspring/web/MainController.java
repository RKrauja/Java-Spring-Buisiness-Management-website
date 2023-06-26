package com.reiniskr.registrationloginspring.web;

import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.repository.ProductRepository;
import com.reiniskr.registrationloginspring.repository.UserRepository;
import com.reiniskr.registrationloginspring.web.dto.ProductDto;
import com.reiniskr.registrationloginspring.web.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    boolean UserLoggedIn = false;

    private User currentUser;

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
    public String loginUserAccount(@ModelAttribute("user") UserLoginDto loginDto) {
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
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user",currentUser);
        if(!UserLoggedIn)
            return "/login";
        return "Dashboard";
    }

    @PostMapping("/dashboard")
    public String getFromDashboard(@ModelAttribute("product")ProductDto productDto){
        System.out.println(productDto);

        return "login";
    }


    @GetMapping("/adminDashboard")
    public String showAdminDashboard(){
        if (!currentUser.isAdmin())
            return "/login";
        return "AdminDashboard";

    }

    @PostMapping("/adminDashboard")
    public String loginUserAccount(@ModelAttribute("newProd") ProductDto productDto) {
        System.out.println(productDto.getName());
        return "redirect:/dashboard";
    }


}
/*
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }*/