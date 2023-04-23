package com.example.computershop.Controllers;

import com.example.computershop.DTOs.UserDTO;
import com.example.computershop.Entities.User;
import com.example.computershop.Mappers.UserMapper;
import com.example.computershop.Repositories.UserRepository;
import com.example.computershop.Services.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class UserController {
    @Value(upload.path)
    private String imagePath;
    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user){
//        if(userService.addUser(userMapper.UserDTOToUser(userDTO))){
//            return "login";
//        }
        if(userService.addUser(user)){
            return "login";
        }
        else
            return "registration";
    }

    @GetMapping("/avatar/{name}")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String name){
        File file = null;
        byte[] imageInBytes = null;
        try{
            file = new File(imagePath + "Avatars/" + name + ".png");
            imageInBytes = Files.readAllBytes(file.toPath());
        } catch (Exception ex) {
            file = new File(imagePath + "Avatars/Anonymous.png");
            imageInBytes = Files.readAllBytes(file.toPath());
        }
        finally {
            return imageInBytes;
        }
    }
}
