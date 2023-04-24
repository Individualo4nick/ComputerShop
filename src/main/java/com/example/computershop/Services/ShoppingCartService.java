package com.example.computershop.Services;

import com.example.computershop.Entities.ShoppingCart;
import com.example.computershop.Entities.User;

import java.util.List;

public interface ShoppingCartService {

    void addComponent(Long componentid, User user);

    List<ShoppingCart> getShoppingCartByUser(User user);

    boolean pay(List<Long> componentsId, User user);

    void deleteFromShoppingCart(Long id);
}
