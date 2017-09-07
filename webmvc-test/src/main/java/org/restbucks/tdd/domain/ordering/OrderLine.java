package org.restbucks.tdd.domain.ordering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.restbucks.tdd.domain.catalog.Size;

@AllArgsConstructor
@Getter
public class OrderLine {

    private String item;
    private Size size;
    private int quantity;
    private int price;

    public int subtotalAmount() {
        return quantity * price;
    }

}
