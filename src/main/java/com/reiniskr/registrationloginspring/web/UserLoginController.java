package com.reiniskr.registrationloginspring.web;

import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.repository.UserRepository;
import com.reiniskr.registrationloginspring.web.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("user")
    public UserLoginDto userLoginDto(){
        return new UserLoginDto();
    }


    @GetMapping
    public String showLoginForm(){
        return "login";
    }
    @PostMapping
    public String loginUserAccount(@ModelAttribute("user") UserLoginDto loginDto) {
        System.out.println("Entered email: "+loginDto.getEmail());
        System.out.println("Entered password: "+loginDto.getPassword());
        User existingUser = userRepository.findByEmail(loginDto.getEmail());

        if (existingUser != null){
            if (existingUser.getPassword().equals(loginDto.getPassword())) {
                return "Dashboard";
            }
        }
        return "redirect:/login?error";

//        return "login";

//        return "redirect:/login?success";
    }

}
