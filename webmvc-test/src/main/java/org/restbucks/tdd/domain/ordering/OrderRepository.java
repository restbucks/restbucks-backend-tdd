package org.restbucks.tdd.domain.ordering;

import java.util.List;
import org.restbucks.tdd.domain.ordering.Order.Identity;
import org.restbucks.tdd.domain.ordering.Order.Status;

public interface OrderRepository {

    Order findOne(Identity identity);

    void save(Order order);

    List<Order> findByStatus(Status status);
}
