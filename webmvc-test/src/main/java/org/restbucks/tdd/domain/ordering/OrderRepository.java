package org.restbucks.tdd.domain.ordering;

import org.restbucks.tdd.domain.ordering.Order.Identity;

public interface OrderRepository {

    Order findOne(Identity identity);
}
