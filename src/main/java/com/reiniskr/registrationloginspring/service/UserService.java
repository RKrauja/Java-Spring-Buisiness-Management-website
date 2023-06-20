package com.reiniskr.registrationloginspring.service;

import com.reiniskr.registrationloginspring.model.User;
import com.reiniskr.registrationloginspring.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
