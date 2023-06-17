package com.concert.controllers;

import com.concert.entities.LoginDto;
import com.concert.entities.RegisterDto;
import com.concert.entities.User;
import com.concert.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}
