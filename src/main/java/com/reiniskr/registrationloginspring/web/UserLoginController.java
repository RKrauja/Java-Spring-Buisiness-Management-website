package com.reiniskr.registrationloginspring.web;

import com.reiniskr.registrationloginspring.service.UserService;
import com.reiniskr.registrationloginspring.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserLoginController {


    private UserService userService;

    public UserLoginController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(){
        return "login";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/login?success";
    }

}
