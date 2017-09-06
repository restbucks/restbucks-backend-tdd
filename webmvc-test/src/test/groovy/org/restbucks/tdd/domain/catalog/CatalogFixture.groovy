package org.restbucks.tdd.domain.catalog

class CatalogFixture {
    def target = new Catalog()

    CatalogFixture() {
        target.setId(Catalog.Identity.next())
        target.setName("Latte")
        target.setSize(Size.MEDIUM)
    }

    static CatalogFixture aCatalog() {
        new CatalogFixture()
    }

    def withName(String name) {
        target.setName(name)
        this
    }

    def withSize(Size size) {
        target.setSize(size)
        this
    }

    def build() {
        target
    }
}
