package com.example.computershop.Services;

import com.example.computershop.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    boolean addUser(User user);
    String getName(User user);
    String getSurname(User user);
    String getEmail(User user);
    String getCountry(User user);
    Long getId(User user);
    void changeImage(User user, MultipartFile img);
}
