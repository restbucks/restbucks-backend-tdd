package org.restbucks.tdd.jpa;

import static org.restbucks.tdd.domain.ordering.QOrder.order;

import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.restbucks.tdd.domain.ordering.Order;
import org.restbucks.tdd.domain.ordering.Order.Identity;
import org.restbucks.tdd.domain.ordering.Order.Status;
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

    @Transactional
    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return new JPAQuery<Order>(entityManager)
            .from(order)
            .where(order.status.eq(status))
            .fetch();
    }
}
