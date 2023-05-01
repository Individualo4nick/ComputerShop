package com.example.computershop;

import com.example.computershop.Entities.ShoppingCart;

import java.util.List;

public class ForSum {
    public static int sum(List<ShoppingCart> shoppingCarts) {
        return shoppingCarts.stream().mapToInt(b -> b.getComputerComponent().getPrice()*b.getCount()).sum();
    }
}
