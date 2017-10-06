package org.restbucks.tdd.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.restbucks.tdd.domain.ordering.Order;
import org.restbucks.tdd.domain.ordering.Order.Identity;
import org.restbucks.tdd.domain.ordering.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findOne(Identity identity) {
        return entityManager.find(Order.class, identity);
    }
}
