package org.restbucks.tdd.jpa

import de.danielbechler.diff.ObjectDifferBuilder
import de.danielbechler.diff.node.DiffNode
import org.junit.Test
import org.restbucks.tdd.domain.ordering.Order
import org.restbucks.tdd.domain.ordering.OrderRepository
import org.springframework.beans.factory.annotation.Autowired

import static org.restbucks.tdd.domain.ordering.OrderFixture.anOrder

class JpaOrderRepositoryTest extends AbstractJpaTest {

    @Autowired
    private OrderRepository subject

    @Test
    void "find by id"() {

        Order order = anOrder().build()

        entityManager.persist(order)

        Order after = subject.findOne(order.id)

        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(order, after)

        assert !diff.hasChanges()
    }
}
