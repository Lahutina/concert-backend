package com.concert.services.impl;

import com.concert.dao.UserDao;
import com.concert.entities.LoginDto;
import com.concert.entities.RegisterDto;
import com.concert.entities.Role;
import com.concert.entities.User;
import com.concert.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User login(LoginDto loginDto) {
        User user = userDao.findByEmail(loginDto.email());
        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            return null;
        }

        return user;
    }

    @Override
    public User register(RegisterDto registerDto) {
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
        return user;
    }
}
