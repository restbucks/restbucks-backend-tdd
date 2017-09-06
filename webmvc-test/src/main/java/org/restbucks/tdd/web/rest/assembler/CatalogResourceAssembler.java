package org.restbucks.tdd.web.rest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.restbucks.tdd.domain.catalog.Catalog;
import org.restbucks.tdd.web.rest.CatalogRestController;
import org.restbucks.tdd.web.rest.resource.CatalogResource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CatalogResourceAssembler extends ResourceAssemblerSupport<Catalog, CatalogResource> {

    private final ModelMapper modelMapper;

    public CatalogResourceAssembler() {
        super(CatalogRestController.class, CatalogResource.class);
        this.modelMapper = new ModelMapper();
    }

    public Resources<CatalogResource> toHalResources(List<Catalog> all) {
        return new Resources<>(toResources(all),
            linkTo(methodOn(CatalogRestController.class).all()).withSelfRel());
    }

    @Override

    public CatalogResource toResource(Catalog entity) {
        return modelMapper.map(entity, CatalogResource.class);
    }
}
