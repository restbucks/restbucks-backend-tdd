package org.restbucks.tdd.domain.catalog;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogRepository {

    public List<Catalog> findAll() {
        return new ArrayList<>();
    }

}
