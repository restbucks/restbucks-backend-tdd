package org.restbucks.tdd.application;


import org.restbucks.tdd.command.PlaceOrderCommand;
import org.restbucks.tdd.domain.ordering.Order;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrderCommandHandler {

    public Order handle(PlaceOrderCommand command) {
        return null;
    }
}
