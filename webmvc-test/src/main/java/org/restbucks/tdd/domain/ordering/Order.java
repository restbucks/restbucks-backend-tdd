package org.restbucks.tdd.domain.ordering;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Getter
@NoArgsConstructor(access = PRIVATE)
public class Order {

    private Identity id;

    private Location location;

    private Order(Identity id) {
        this.id = id;
    }

    public void with(Location location) {
        this.location = location;
    }

    public static Order newOrder() {
        return new Order(Identity.next());
    }

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
