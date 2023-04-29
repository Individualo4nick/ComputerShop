package com.example.computershop.Controllers;

import com.example.computershop.Entities.Comment;
import com.example.computershop.Entities.User;
import com.example.computershop.ForFilter;
import com.example.computershop.Services.ComputerComponentService;
import com.example.computershop.Services.ShoppingCartService;
import com.example.computershop.Services.UserService;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.util.List;


@Controller
@RequestMapping("/component")
public class ComputerComponentController {
    @Value("${files.path}")
    private String imagePath;
    private final ComputerComponentService computerComponentService;
    private final ShoppingCartService shoppingCartService;

    public ComputerComponentController(ComputerComponentService computerComponentService, ShoppingCartService shoppingCartService) {
        this.computerComponentService = computerComponentService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/all")
    public String getAllComponents(Model model) {
        model.addAttribute("allCategory", computerComponentService.getAllCategory());
        model.addAttribute("allProducer", computerComponentService.getAllProducer());
        model.addAttribute("allWarranty", computerComponentService.getAllWarranty());
        model.addAttribute("allComponent", computerComponentService.getAllComponent());
        model.addAttribute("path", imagePath);
        return "get_all_component";
    }

    @PostMapping(value = "/all/filter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getFilterComponents(@RequestBody ForFilter filter, Model model) {
        model.addAttribute("allCaregory", computerComponentService.getAllCategory());
        model.addAttribute("allProducer", computerComponentService.getAllProducer());
        model.addAttribute("allWarranty", computerComponentService.getAllWarranty());
        model.addAttribute("allComponent", computerComponentService.filterComponent(filter));
        model.addAttribute("path", imagePath);
        return "get_filter_components";
    }

    @GetMapping("/{id}")
    public String getComponentById(@PathVariable Long id, Model model) {
        model.addAttribute("componentData", computerComponentService.getComponentById(id));
        model.addAttribute("comments", computerComponentService.getAllComment(id));
        return "component_page";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addComponent/{componentid}")
    public String addToBasket(@PathVariable Long componentid, @AuthenticationPrincipal User user) {
        if (user != null) {
            shoppingCartService.addComponent(componentid, user);
        }
        return "redirect:/component/" + componentid;
    }

    @GetMapping("/image/{name}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name) throws IOException {
        File serverFile = computerComponentService.getComponentImage(name);
        return Files.readAllBytes(serverFile.toPath());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shopping_cart")
    public String getShoppingCart(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("cartData", shoppingCartService.getShoppingCartByUser(user));
        return "shopping_cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shopping_cart")
    public String getShoppingCart(@RequestParam List<Long> components_id, @AuthenticationPrincipal User user, Model model) {
        shoppingCartService.pay(components_id, user);
        return "redirect:/component/shopping_cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete_from_shopping_cart")
    public String deleteFromBasket(@AuthenticationPrincipal User user, @RequestParam Long id) {
        shoppingCartService.deleteFromShoppingCart(id);
        return "redirect:/component/shopping_cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/send_comment")
    public String getShoppingCart(@RequestParam Long component_id, @RequestParam String some_comment, @AuthenticationPrincipal User user, Model model) {
        Comment comment = new Comment(user.getName(), component_id, user.getId(), some_comment);
        computerComponentService.addComment(comment);
        return "redirect:/component/" + component_id;
    }
}
