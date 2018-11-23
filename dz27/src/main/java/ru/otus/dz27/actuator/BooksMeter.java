package ru.otus.dz27.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BooksMeter {

    private final Logger logger = LoggerFactory.getLogger("Meter");

    private final Counter counter;

    public BooksMeter(MeterRegistry registry) {
        this.counter = registry.counter("adding.books");
    }

    public void handleMessage(String message) {
        this.counter.increment();
        logger.info(message);
    }
}
