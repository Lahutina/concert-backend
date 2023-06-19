package com.concert.services.impl;

import com.concert.dao.UserDao;
import com.concert.entities.dto.LoginDto;
import com.concert.entities.dto.RegisterDto;
import com.concert.entities.Role;
import com.concert.entities.User;
import com.concert.services.AuthService;
import com.concert.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        User user = userDao.findByEmail(loginDto.email());
        if (user == null) {
            return null;
        } else if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            return null;
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));

        return jwtService.generateToken(user);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (!registerDto.password().equals(registerDto.confirmPassword()))
            return null;
        if (userDao.findByEmail(registerDto.email()) != null) {
            return null;
        }
        User user = new User();
        user.setFirstName(registerDto.firstName());
        user.setLastName(registerDto.lastName());
        user.setEmail(registerDto.email());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        userDao.save(user);

        return jwtService.generateToken(user);
    }
}
