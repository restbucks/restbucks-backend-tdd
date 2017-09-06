package org.restbucks.tdd.web.rest.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "order", collectionRelation = "orders")
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderResource extends ResourceSupport {

    private String location;
}
