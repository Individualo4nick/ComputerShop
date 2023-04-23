package com.example.computershop.Services.Impl;

import com.example.computershop.Config.SecurityConfig;
import com.example.computershop.Entities.Enums.Role;
import com.example.computershop.Entities.User;
import com.example.computershop.Repositories.UserRepository;
import com.example.computershop.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public boolean addUser(User user) {
        if(userRepository.getByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setRole(Role.USER);
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByName(username);
    }
}
