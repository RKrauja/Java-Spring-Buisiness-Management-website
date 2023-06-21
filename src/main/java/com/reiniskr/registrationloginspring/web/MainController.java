package com.reiniskr.registrationloginspring.web;


//import com.reiniskr.registrationloginspring.model.User;
//import com.reiniskr.registrationloginspring.repository.UserRepository;
//import com.reiniskr.registrationloginspring.web.dto.UserLoginDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class MainController {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @GetMapping("/login")
//    public String login(){
//        User a = userRepository.findByEmail("krauja@gmail.com");
//        System.out.println("First User: "+ a.getPassword());
//
//        return "login";
//    }
//    @PostMapping("/login")
//    public String registerUserAccount(UserLoginDto userLoginDto, Model model) {
//        String username = userLoginDto.getUsername();
//        String password = userLoginDto.getPassword();
//        System.out.println("Entered username is: "+ username);
//        System.out.println("Entered password is: "+ password);
//        return "redirect:/login?success";
//    }
//


//}

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//    @PostMapping("/login")
//    public String registerUserAccount(@ModelAttribute("user")User user) {
//        System.out.println(user);
//        return "redirect:/login?success";
//    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}