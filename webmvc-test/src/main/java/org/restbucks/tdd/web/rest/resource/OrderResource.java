package org.restbucks.tdd.web.rest.resource;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "order", collectionRelation = "orders")
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderResource extends ResourceSupport {

    private String location;

    private List<OrderLine> orderLines = new ArrayList<>();

    @Data
    public static class OrderLine {

        private String item;
        private String size;
        private int quantity;
        private int price;
    }
}
