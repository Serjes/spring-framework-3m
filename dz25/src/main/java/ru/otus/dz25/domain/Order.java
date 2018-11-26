package ru.otus.dz25.domain;

import lombok.Data;

@Data
public class Order {
    private int id;
    private String pizzaName;
    private int pizzaSize;

    public Order(String pizzaName, int pizzaSize){
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
    }
}
