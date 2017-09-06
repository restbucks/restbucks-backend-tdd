package org.restbucks.tdd.web.rest.assembler;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.restbucks.tdd.domain.ordering.Order;
import org.restbucks.tdd.web.rest.OrderRestController;
import org.restbucks.tdd.web.rest.resource.OrderResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderResourceAssembler extends ResourceAssemblerSupport<Order, OrderResource> {

    private final ModelMapper modelMapper;

    public OrderResourceAssembler() {
        super(OrderRestController.class, OrderResource.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public OrderResource toResource(Order entity) {
        OrderResource resource = modelMapper.map(entity, OrderResource.class);
        resource.add(linkTo(methodOn(OrderRestController.class).findOne(entity.getId().getValue()))
            .withSelfRel());
        return resource;
    }
}
