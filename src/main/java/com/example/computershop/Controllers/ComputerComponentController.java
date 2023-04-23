package com.example.computershop.Controllers;

import com.example.computershop.ForFilter;
import com.example.computershop.Services.ComputerComponentService;
import com.example.computershop.Services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping("/component")
public class ComputerComponentController {
    @Value("${files.path}")
    private String imagePath;
    private final ComputerComponentService computerComponentService;
    public ComputerComponentController(ComputerComponentService computerComponentService) {
        this.computerComponentService = computerComponentService;
    }

    @GetMapping("/all")
    public String getAllComponents(Model model){
        model.addAttribute("allWarranty", computerComponentService.getAllWarranty());
        model.addAttribute("allComponent", computerComponentService.getAllComponent());
        model.addAttribute("path", imagePath);
        return "get_all_components";
    }

    @PostMapping(value = "/all/filter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getFilterComponents(@RequestBody ForFilter filter, Model model){
        model.addAttribute("allWarranty", computerComponentService.getAllWarranty());
        model.addAttribute("allComponent", computerComponentService.filterComponent(filter  ));
        return "get_filter_components";
    }

    @GetMapping("/{id}")
    public String getComponentById(@PathVariable Long id, Model model) {
        model.addAttribute("componentData", computerComponentService.getClothById(id));
        model.addAttribute("comments", computerComponentService.getAllComment(id));
        return "component_page";
    }
}
