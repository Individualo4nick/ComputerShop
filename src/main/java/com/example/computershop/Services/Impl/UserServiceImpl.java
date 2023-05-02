package com.example.computershop.Services.Impl;

import com.example.computershop.Config.SecurityConfig;
import com.example.computershop.Entities.Enums.Role;
import com.example.computershop.Entities.User;
import com.example.computershop.Repositories.UserRepository;
import com.example.computershop.Services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Value("${files.path}")
    private String filesPath;
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
    public String getName(User user) {
        return user.getName();
    }

    @Override
    public String getSurname(User user) {
        return user.getSurname();
    }

    @Override
    public String getEmail(User user) {
        return user.getEmail();
    }

    @Override
    public String getCountry(User user) {
        return user.getCountry();
    }

    @Override
    public Long getId(User user) {
        return user.getId();
    }

    @Override
    public void changeImage(User user, MultipartFile img) {
        File f = new File(filesPath + "/imageOfUser/");
        try {
            if (!img.isEmpty()) {
                File[] matchingFiles = f.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith(user.getId().toString());
                    }
                });
                matchingFiles[0].delete();
                try {
                    img.transferTo(new File(filesPath + "/imageOfUser/" + user.getId().toString()
                            + "." + Objects.requireNonNull(img.getOriginalFilename()).split("\\.")[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            if (!img.isEmpty()) {
                try {
                    img.transferTo(new File(filesPath + "/imageOfUser/" + user.getId().toString()
                            + "." + Objects.requireNonNull(img.getOriginalFilename()).split("\\.")[1]));
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByName(username);
    }
}
