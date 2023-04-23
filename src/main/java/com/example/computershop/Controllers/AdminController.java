package com.example.computershop.Controllers;

import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.Mappers.UserMapper;
import com.example.computershop.Services.ComputerComponentService;
import com.example.computershop.Services.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

public class AdminController {
    private final ComputerComponentService computerComponentService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    public AdminController(ComputerComponentService computerComponentService) {
        this.computerComponentService = computerComponentService;
    }
    @GetMapping("/create_page")
    public String getPage(){
        return "create_page";
    }
    @PostMapping("/create_page")
    public String postPage(@RequestParam String title, @RequestParam String producer,
                           @RequestParam String discription, @RequestParam int price,
                           @RequestParam int warranty_in_month, @RequestParam MultipartFile img) throws NoSuchAlgorithmException {
        ComputerComponent computerComponent = new ComputerComponent(title, producer, discription, price, warranty_in_month);
        computerComponentService.addComponent(computerComponent, img);
        return "redirect:/cloth/all";
    }
    @PostMapping("/delete_comment")
    public String DeleteComment(@RequestParam Long idComment, @RequestParam Long idComponent) {
        computerComponentService.deleteComment(idComment);
        return "redirect:/cloth/" + idComponent;
    }
}
