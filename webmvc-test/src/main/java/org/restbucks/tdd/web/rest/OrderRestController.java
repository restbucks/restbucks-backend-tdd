package org.restbucks.tdd.web.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.tdd.domain.ordering.Order;
import org.restbucks.tdd.domain.ordering.OrderRepository;
import org.restbucks.tdd.web.rest.assembler.OrderResourceAssembler;
import org.restbucks.tdd.web.rest.resource.OrderResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    @NonNull
    private final OrderRepository orderRepository;

    @NonNull
    private final OrderResourceAssembler orderResourceAssembler;

    @GetMapping("/rel/orders/{id}")
    public OrderResource findOne(@PathVariable String id) {
        return orderResourceAssembler.toResource(orderRepository.findOne(Order.Identity.of(id)));
    }

}
