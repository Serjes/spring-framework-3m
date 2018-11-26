package ru.otus.dz25.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.dz25.domain.Order;
import ru.otus.dz25.domain.Pizza;

@MessagingGateway
public interface Distributor {

    @Gateway(requestChannel = "order", replyChannel = "cooking")
    Pizza process(Order orderItem);
}
