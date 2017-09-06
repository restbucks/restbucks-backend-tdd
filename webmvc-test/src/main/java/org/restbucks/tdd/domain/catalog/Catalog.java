package org.restbucks.tdd.domain.catalog;

import static lombok.AccessLevel.PROTECTED;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Getter
@Setter(PROTECTED)
@ToString
@EqualsAndHashCode(of = "id")
public class Catalog {

    private Identity id;
    private String name;
    private Size size;
    private int price;

    @Value
    public static class Identity {

        private String value;

        public static Identity of(String value) {
            return new Identity(value);
        }

        public static Identity next() {
            return of(UUID.randomUUID().toString());
        }
    }
}
