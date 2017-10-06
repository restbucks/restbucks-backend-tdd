package org.restbucks.tdd.domain.ordering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Entity
@Table(name = "orders")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    private Identity id;

    @Transient
    private Location location;

    @Transient
    private Status status;

    @Transient
    private List<OrderLine> orderLines = new ArrayList<>();

    private Order(Identity id) {
        this.id = id;
    }

    public void with(Location location) {
        this.location = location;
    }

    public void with(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public static Order newOrder() {
        return new Order(Identity.next());
    }


    public int totalAmount() {
        return orderLines.stream().mapToInt(OrderLine::subtotalAmount).sum();
    }

    @Embeddable
    @Value
    public static class Identity implements Serializable {

        @Column(name = "id")
        private String value;

        public static Identity of(String value) {
            return new Identity(value);
        }

        public static Identity next() {
            return of(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    /**
     * Enumeration for all the statuses an {@link Order} can be in.
     */
    public enum Status {

        /**
         * Placed, but not payed yet. Still changeable.
         */
        PAYMENT_EXPECTED,

        /**
         * {@link Order} was payed. No changes allowed to it anymore.
         */
        PAID,

        /**
         * The {@link Order} is currently processed.
         */
        PREPARING,

        /**
         * The {@link Order} is ready to be picked up by the customer.
         */
        READY,

        /**
         * The {@link Order} was completed.
         */
        TAKEN;
    }
}
