package org.restbucks.tdd.web.rest.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "catalog", collectionRelation = "catalogs")
@EqualsAndHashCode(callSuper = false)
@Data
public class CatalogResource extends ResourceSupport {

    private String name;
    private String size;
}
