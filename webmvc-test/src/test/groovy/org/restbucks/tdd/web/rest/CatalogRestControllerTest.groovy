package org.restbucks.tdd.web.rest

import org.junit.Test
import org.restbucks.tdd.domain.catalog.CatalogRepository
import org.restbucks.tdd.web.AbstractWebMvcTest
import org.restbucks.tdd.web.rest.assembler.CatalogResourceAssembler
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean

import static org.hamcrest.Matchers.is
import static org.mockito.BDDMockito.given
import static org.restbucks.tdd.domain.catalog.CatalogFixture.aCatalog
import static org.restbucks.tdd.domain.catalog.Size.LARGE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [
    CatalogRestController,
    CatalogResourceAssembler
])
class CatalogRestControllerTest extends AbstractWebMvcTest {

    @MockBean
    private CatalogRepository catalogRepository

    @Test
    void "it should return all catalogs"() {

        def first = aCatalog().withName("Espresso").withSize(LARGE).build()
        def second = aCatalog().build()

        given(catalogRepository.findAll()).willReturn([
            first,
            second
        ])

        // @formatter:off
        this.mockMvc.perform(get("/rel/catalogs"))
	            .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.catalogs[0].name", is(first.name)))
                .andExpect(jsonPath("_embedded.catalogs[0].size", is(first.size.name())))
        // @formatter:on
    }

}
