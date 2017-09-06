package org.restbucks.tdd.domain.ordering

import org.restbucks.tdd.domain.catalog.Catalog
import org.restbucks.tdd.domain.catalog.Size

class OrderFixture {
    def target = new Order()

    OrderFixture() {
        target.setId(Catalog.Identity.next())
        target.setName("Latte")
        target.setSize(Size.MEDIUM)
    }

    static OrderFixture anOrder() {
        new OrderFixture()
    }

    def build() {
        target
    }
}
