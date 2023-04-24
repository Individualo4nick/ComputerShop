package com.example.computershop.Services.Impl;

import com.example.computershop.Entities.ShoppingCart;
import com.example.computershop.Entities.User;
import com.example.computershop.Services.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public void addComponent(Long componentid, User user) {

    }

    @Override
    public List<ShoppingCart> getShoppingCartByUser(User user) {
        return null;
    }

    @Override
    public boolean pay(List<Long> componentsId, User user) {
        return false;
    }

    @Override
    public void deleteFromShoppingCart(Long id) {

    }
}
