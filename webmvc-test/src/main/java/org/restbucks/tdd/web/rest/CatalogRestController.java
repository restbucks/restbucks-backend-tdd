package org.restbucks.tdd.web.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.tdd.domain.catalog.CatalogRepository;
import org.restbucks.tdd.domain.catalog.Size;
import org.restbucks.tdd.web.rest.assembler.CatalogResourceAssembler;
import org.restbucks.tdd.web.rest.resource.CatalogResource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CatalogRestController {

    @NonNull
    private final CatalogRepository catalogRepository;

    @NonNull
    private final CatalogResourceAssembler catalogResourceAssembler;

    @GetMapping("/rel/catalogs")
    public Resources<CatalogResource> all() {
        return catalogResourceAssembler.toHalResources(catalogRepository.findAll());
    }

    @GetMapping("/rel/catalogs/search")
    public Resources<CatalogResource> filter(@RequestParam("size") Size size) {
        return catalogResourceAssembler.toHalResources(catalogRepository.findBy(size));
    }

}
