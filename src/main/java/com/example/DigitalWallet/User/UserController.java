package com.example.DigitalWallet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalWallet.AuthResponse.AuthResponse;
import com.example.DigitalWallet.dto.UserDto;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;
     @PostMapping("auth/users/register")
    public String userRegistration(@RequestBody @Valid UserDto userDetails) {
         return userService.userRegistration(userDetails);
    }
    
    @PostMapping("auth/users/login")
    public AuthResponse userLogin(@RequestBody @Valid UserDto user) {
        return userService.loginuser(user);
    }
}
