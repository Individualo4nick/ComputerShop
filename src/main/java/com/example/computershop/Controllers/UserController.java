package com.example.computershop.Controllers;

import com.example.computershop.Entities.User;
import com.example.computershop.Services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Value("${files.path}")
    private String imagePath;
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user){
        if(userService.addUser(user)){
            return "login";
        }
        else
            return "registration";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("userid", userService.getId(user));
        model.addAttribute("name", userService.getName(user));
        model.addAttribute("surname", userService.getSurname(user));
        model.addAttribute("email", userService.getEmail(user));
        model.addAttribute("country", userService.getCountry(user));
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String postProfile(@AuthenticationPrincipal User user, @RequestParam MultipartFile img) throws NoSuchAlgorithmException {
        userService.changeImage(user, img);
        return "redirect:/profile";
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
