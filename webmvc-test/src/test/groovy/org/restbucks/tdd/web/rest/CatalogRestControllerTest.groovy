package org.restbucks.tdd.web.rest

import com.github.hippoom.wiremock.contract.verifier.anntation.Contract
import org.junit.Test
import org.restbucks.tdd.domain.catalog.CatalogRepository
import org.restbucks.tdd.domain.catalog.Size
import org.restbucks.tdd.web.AbstractWebMvcTest
import org.restbucks.tdd.web.rest.assembler.CatalogResourceAssembler
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean

import static org.mockito.BDDMockito.given
import static org.restbucks.tdd.domain.catalog.CatalogFixture.aCatalog
import static org.restbucks.tdd.domain.catalog.Size.LARGE
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters

@WebMvcTest(controllers = [
    CatalogRestController,
    CatalogResourceAssembler
])
class CatalogRestControllerTest extends AbstractWebMvcTest {

    @MockBean
    private CatalogRepository catalogRepository

    @Contract("find_catalogs.json")
    @Test
    void "it should return all catalogs"() {

        def first = aCatalog().withName("Espresso").withSize(LARGE).build()
        def second = aCatalog().withName("Latte").withSize(LARGE).build()

        given(catalogRepository.findAll()).willReturn([
            first,
            second
        ])

        // @formatter:off
        this.mockMvc.perform(contractVerifier.requestPattern())
	            .andExpect(contractVerifier.responseDefinition())
        // @formatter:on
    }

    @Contract("find_catalogs_by_size.json")
    @Test
    void "it should filter catalogs by size"() {

        def first = aCatalog().withName("Espresso").withSize(LARGE).build()
        def second = aCatalog().withName("Latte").withSize(LARGE).build()

        given(catalogRepository.findBy(LARGE)).willReturn([
            first,
            second
        ])

        // @formatter:off
        this.mockMvc.perform(contractVerifier.requestPattern())
	            .andExpect(contractVerifier.responseDefinition())
                .andDo(document('catalog/find_catalogs_by_size',
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestParameters(
                                parameterWithName("size").description("filter by size, should be one of ${Size.values()}")
                            ),
                            responseFields(
                                subsectionWithPath("_links").ignored()//validate by links() block
                            )
                            .andWithPrefix("_embedded.catalogs[].",
                                fieldWithPath("name")
                                    .description("The item name"),
                                fieldWithPath("size")
                                    .description("The item size, should be one of ${Size.values()}")
                            ),
                            links(halLinks(),
                                    linkWithRel("self")
                                            .description("link to refresh the catalogs")
                            )
                ))
        // @formatter:on
    }

}
