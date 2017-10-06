package org.restbucks.tdd.jpa

import de.danielbechler.diff.ObjectDifferBuilder
import de.danielbechler.diff.node.DiffNode
import org.junit.Test
import org.restbucks.tdd.domain.ordering.Order
import org.restbucks.tdd.domain.ordering.OrderRepository
import org.springframework.beans.factory.annotation.Autowired

import static org.restbucks.tdd.domain.ordering.Order.Status.PAYMENT_EXPECTED
import static org.restbucks.tdd.domain.ordering.OrderFixture.anOrder

class JpaOrderRepositoryTest extends AbstractJpaTest {

    @Autowired
    private OrderRepository subject

    @Test
    void "find by id"() {

        Order order = anOrder().build()

        subject.save(order)

        Order after = subject.findOne(order.id)

        DiffNode diff = ObjectDifferBuilder.startBuilding()
            .inclusion().exclude().propertyName("location").propertyName("orderLines")
            .and().build()
            .compare(order, after)

        assert !diff.hasChanges()
    }

    @Test
    void "find by status"() {


        List<Order> before = subject.findByStatus(PAYMENT_EXPECTED)

        Order shouldBeFound = anOrder().build()
        Order paid = anOrder().isPaid().build()

        subject.save(shouldBeFound)
        subject.save(paid)


        List<Order> after = subject.findByStatus(PAYMENT_EXPECTED)

        assert after.size() == before.size() + 1
    }

}
