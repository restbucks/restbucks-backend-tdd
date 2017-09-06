package org.restbucks.tdd.domain.catalog;

import static lombok.AccessLevel.PROTECTED;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(PROTECTED)
@ToString
@EqualsAndHashCode(of = "id")
public class Catalog {

    private String id;
    private String name;
    private Size size;
    private int price;
}
