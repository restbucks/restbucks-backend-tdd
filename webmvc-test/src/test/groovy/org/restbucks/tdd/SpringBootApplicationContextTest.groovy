package org.restbucks.tdd

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@RunWith(SpringRunner)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class SpringBootApplicationContextTest {

    @Test
    void "it should load the context"() {

    }
}
