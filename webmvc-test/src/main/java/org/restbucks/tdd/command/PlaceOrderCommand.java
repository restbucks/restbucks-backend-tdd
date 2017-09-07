package org.restbucks.tdd.command;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.restbucks.tdd.domain.catalog.Size;
import org.restbucks.tdd.domain.ordering.Location;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderCommand {

    private Location location;

    private List<Item> items = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {

        private String item;
        private Size size;
        private int quantity;
        private int price;
    }

}
