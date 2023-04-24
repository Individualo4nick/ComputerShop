package com.example.computershop.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="computer_components")
public class ComputerComponent{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String producer;
    private String description;
    private String category;
    private int price;
    private int warranty_in_month;

    public ComputerComponent(String title, String producer, String description, String category, int price, int warranty_in_month) {
        this.title = title;
        this.producer = producer;
        this.description = description;
        this.category = category;
        this.price = price;
        this.warranty_in_month = warranty_in_month;
    }
}
