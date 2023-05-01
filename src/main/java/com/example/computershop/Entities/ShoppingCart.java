package com.example.computershop.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="shopping_cart")
public class ShoppingCart{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private ComputerComponent computerComponent;
    private int count = 1;

    public ShoppingCart(User user, ComputerComponent computerComponent) {
        this.user = user;
        this.computerComponent = computerComponent;
    }

    public void incrementCount(){
        this.count++;
    }

    public void decrementCount(){
        this.count--;
    }

    public ShoppingCart() {

    }
}

