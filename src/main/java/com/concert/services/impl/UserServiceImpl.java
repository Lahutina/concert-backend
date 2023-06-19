package com.concert.services.impl;

import com.concert.dao.UserDao;
import com.concert.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public UserDetailsService userDetailsService() {
        return userDao::findByEmail;
    }
}
