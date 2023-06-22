package com.concert.services;

import com.concert.entities.dto.LoginDto;
import com.concert.entities.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
