package org.restbucks.tdd.web

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log

@Configuration
class MockMvcBuilderCustomerizers {

    @Bean
    protected MockMvcBuilderCustomizer alwaysDoLog() {
        new MockMvcBuilderCustomizer() {

            @Override
            void customize(ConfigurableMockMvcBuilder builder) {
                builder.alwaysDo(log())
            }
        }
    }

}
