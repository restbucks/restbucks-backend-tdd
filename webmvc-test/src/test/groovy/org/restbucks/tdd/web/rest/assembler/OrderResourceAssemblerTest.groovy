package org.restbucks.tdd.web.rest.assembler

import org.restbucks.tdd.domain.catalog.Size
import org.restbucks.tdd.domain.ordering.OrderLine
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

import static org.restbucks.tdd.domain.ordering.Location.IN_STORE
import static org.restbucks.tdd.domain.ordering.Order.newOrder

class OrderResourceAssemblerTest extends Specification {

    def subject = new OrderResourceAssembler()

    def setup() {
        HttpServletRequest mockRequest = new MockHttpServletRequest()
        ServletRequestAttributes servletRequestAttributes =
            new ServletRequestAttributes(mockRequest)
        RequestContextHolder.setRequestAttributes(servletRequestAttributes)
    }

    def cleanup() {
        RequestContextHolder.resetRequestAttributes()
    }

    def "it should covert Order to OrderResource"() {

        given: "order"

        def order = newOrder()
        order.with(IN_STORE)
        order.with([
            new OrderLine("Latte", Size.MEDIUM, 1, 28),
            new OrderLine("Cappuccino", Size.LARGE, 1, 32)
        ])

        when: "convert"
        def actual = subject.toResource(order)

        then: "it returns an OrderResource"

        assert actual.location == order.location.name()
        assert actual.totalAmount == order.totalAmount()
        assert actual.orderLines.size() == order.orderLines.size()

        actual.orderLines.eachWithIndex { ol, i ->
            assert ol.item == order.orderLines[i].item
            assert ol.size == order.orderLines[i].size.name()
            assert ol.quantity == order.orderLines[i].quantity
            assert ol.price == order.orderLines[i].price
        }
    }
}
