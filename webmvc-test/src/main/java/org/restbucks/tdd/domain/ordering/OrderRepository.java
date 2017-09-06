package org.restbucks.tdd.domain.ordering;

import org.restbucks.tdd.domain.ordering.Order.Identity;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    public Order findOne(Identity identity) {
        return null;
    }
}
