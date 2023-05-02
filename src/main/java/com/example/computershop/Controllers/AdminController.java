package com.example.computershop.Controllers;

import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.Services.ComputerComponentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

@Controller
public class AdminController {
    private final ComputerComponentService computerComponentService;
    public AdminController(ComputerComponentService computerComponentService) {
        this.computerComponentService = computerComponentService;
    }
    @GetMapping("/create_page")
    public String getPage(){
        return "create_page";
    }
    @PostMapping("/create_page")
    public String postPage(@RequestParam String title, @RequestParam String producer,
                           @RequestParam String discription, @RequestParam String category, @RequestParam int price,
                           @RequestParam int warranty_in_month, @RequestParam MultipartFile img) throws NoSuchAlgorithmException {
        ComputerComponent computerComponent = new ComputerComponent(title, producer, discription, category, price, warranty_in_month);
        computerComponentService.addComponent(computerComponent, img);
        return "redirect:/component/all";
    }
    @PostMapping("/delete_comment")
    public String deleteComment(@RequestParam Long idComment, @RequestParam Long idComponent) {
        computerComponentService.deleteComment(idComment);
        return "redirect:/component/" + idComponent;
    }

    @PostMapping("/delete_page")
    public String deletePage(@RequestParam Long idComponent) {
        computerComponentService.deletePage(idComponent);
        return "redirect:/component/all";
    }
}
