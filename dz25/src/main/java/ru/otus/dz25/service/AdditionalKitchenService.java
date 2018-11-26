package ru.otus.dz25.service;


import org.springframework.stereotype.Service;
import ru.otus.dz25.domain.Order;
import ru.otus.dz25.domain.Pizza;

@Service
public class AdditionalKitchenService {

    public Pizza cook(Order order) throws Exception {
        System.out.println("Cooking " + order.getPizzaName() + " in additional kitchen");
        Thread.sleep(5000);
        System.out.println("Cooking " + order.toString() + " done");
        return new Pizza(order.getPizzaName(), order.getPizzaSize());
    }
}
