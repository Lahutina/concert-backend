package com.concert.services;

import com.concert.entities.LoginDto;
import com.concert.entities.RegisterDto;
import com.concert.entities.User;

public interface UserService {
    User login(LoginDto loginDto);
    User register(RegisterDto registerDto);
}
