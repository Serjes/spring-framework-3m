package ru.otus.dz25.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.dz25.domain.Order;
import ru.otus.dz25.domain.Pizza;

@EnableIntegration
@IntegrationComponentScan
@Configuration
public class IntegrationConfig {

    @Bean
    public DirectChannel order() {
        return MessageChannels.direct().datatype(Order.class).get();
    }

    @Bean
    public PublishSubscribeChannel cooking() {
        return MessageChannels.publishSubscribe().datatype(Pizza.class).get();
    }

    @Bean
    public IntegrationFlow cookingFlow() {
        return IntegrationFlows.from("order")
                .<Order, Boolean>route(order -> order.getId() % 3 == 0,
                        m -> m
                                .subFlowMapping(false, sf -> sf
                                        .handle("mainKitchenService", "cook")
                                )
                                .subFlowMapping(true, sf -> sf
                                        .handle("additionalKitchenService", "cook")
                                )
                )
                .channel("cooking")
                .log()
                .get();
    }

}
