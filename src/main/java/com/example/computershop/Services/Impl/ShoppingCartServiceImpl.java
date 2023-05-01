package com.example.computershop.Services.Impl;

import com.example.computershop.Entities.ComputerComponent;
import com.example.computershop.Entities.ShoppingCart;
import com.example.computershop.Entities.User;
import com.example.computershop.Repositories.ShoppingCartRepository;
import com.example.computershop.Services.ShoppingCartService;
import com.example.computershop.Services.ComputerComponentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ComputerComponentService computerComponentService;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ComputerComponentService computerComponentService, ShoppingCartRepository shoppingCartRepository){
        this.computerComponentService = computerComponentService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addComponent(Long componentid, User user) {
        ComputerComponent computerComponent = computerComponentService.getComponentById(componentid);
        ShoppingCart shoppingCart1 = shoppingCartRepository.findByComputerComponentId(componentid);
        if (shoppingCart1==null) {
            ShoppingCart shoppingCart = new ShoppingCart(user, computerComponent);
            shoppingCartRepository.save(shoppingCart);
        }
        else{
            shoppingCart1.incrementCount();
            shoppingCartRepository.save(shoppingCart1);
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCartByUser(User user) {
        return shoppingCartRepository.findAllByUser(user);
    }

    @Override
    public boolean pay(List<Long> componentsId, User user) {
        shoppingCartRepository.deleteAll(shoppingCartRepository.findAllByListIdsAndUser(componentsId, user));
        return true;
    }

    @Override
    public void deleteFromShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public void incrementInShoppingCart(Long cartid) {
        ShoppingCart shoppingCart1 = shoppingCartRepository.findByShoppingCartId(cartid);
        shoppingCart1.incrementCount();
        shoppingCartRepository.save(shoppingCart1);
    }

    @Override
    public void decrementInShoppingCart(Long cartid) {
        ShoppingCart shoppingCart1 = shoppingCartRepository.findByShoppingCartId(cartid);
        shoppingCart1.decrementCount();
        if (shoppingCart1.getCount()==0)
            shoppingCartRepository.deleteById(cartid);
        else
            shoppingCartRepository.save(shoppingCart1);
    }
}
