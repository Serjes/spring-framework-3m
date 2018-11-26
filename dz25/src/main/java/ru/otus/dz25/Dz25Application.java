package ru.otus.dz25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.dz25.domain.Order;
import ru.otus.dz25.domain.Pizza;
import ru.otus.dz25.domain.PizzaNames;
import ru.otus.dz25.service.Distributor;

import java.util.Random;


@SpringBootApplication
public class Dz25Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Dz25Application.class, args);

        Distributor distributor = ctx.getBean(Distributor.class);

        int i = 1;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order order = generateOrder();
            order.setId(i++);
            System.out.println("New order: " + order.toString());
            Pizza pizza = distributor.process(order);
            System.out.println("Ready food: " + pizza.toString());
        }

    }

    private static Order generateOrder() {
        Random r = new Random();
        PizzaNames[] names = PizzaNames.values();
        return new Order(names[r.nextInt(names.length)].toString(), r.nextInt(Pizza.NUMBER_OF_SIZES - 1) + 1);
    }
}
