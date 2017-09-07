package org.restbucks.tdd.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.restbucks.tdd.domain.ordering.Location;

@AllArgsConstructor
@Data
public class PlaceOrderCommand {

    private Location location;
}
