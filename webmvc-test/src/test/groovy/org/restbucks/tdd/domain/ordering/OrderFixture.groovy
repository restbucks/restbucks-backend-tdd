package org.restbucks.tdd.domain.ordering

import org.restbucks.tdd.domain.catalog.Size

import static org.restbucks.tdd.domain.ordering.Location.IN_STORE

class OrderFixture {
    def target = new Order()

    OrderFixture() {
        target.id = Order.Identity.next()
        target.with(IN_STORE)
        target.with([
            new OrderLine("Latte", Size.MEDIUM, 1, 28),
            new OrderLine("Cappuccino", Size.LARGE, 1, 32)
        ])
    }

    def with(Location location) {
        target.with(location)
        this
    }

    def isPaid() {
        target.status = Order.Status.PAID
        this
    }

    static OrderFixture anOrder() {
        new OrderFixture()
    }

    def build() {
        target
    }


}
