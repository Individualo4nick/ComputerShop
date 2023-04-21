package com.example.computershop.Services;

import com.example.computershop.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
public interface UserService extends UserDetailsService {
    boolean addUser(User user);
}
