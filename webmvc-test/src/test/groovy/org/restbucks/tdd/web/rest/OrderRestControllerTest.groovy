package org.restbucks.tdd.web.rest

import org.junit.Test
import org.restbucks.tdd.domain.ordering.OrderRepository
import org.restbucks.tdd.web.AbstractWebMvcTest
import org.restbucks.tdd.web.rest.assembler.OrderResourceAssembler
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean

import static org.hamcrest.Matchers.is
import static org.mockito.BDDMockito.given
import static org.restbucks.tdd.domain.ordering.Location.TAKE_AWAY
import static org.restbucks.tdd.domain.ordering.Order.newOrder
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [
    OrderRestController,
    OrderResourceAssembler
])
class OrderRestControllerTest extends AbstractWebMvcTest {

    @MockBean
    private OrderRepository orderRepository

    @Test
    void "it should return an order"() {

        def order = newOrder()
        order.with(TAKE_AWAY)

        given(orderRepository.findOne(order.id)).willReturn(order)

        // @formatter:off
        this.mockMvc.perform(get("/rel/orders/${order.id.value}"))
	            .andExpect(status().isOk())
                .andExpect(jsonPath("location", is(order.location.name())))
        // @formatter:on
    }

}
