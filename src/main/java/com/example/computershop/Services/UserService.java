package com.example.computershop.Services;

import com.example.computershop.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {
    boolean addUser(User user);
}
