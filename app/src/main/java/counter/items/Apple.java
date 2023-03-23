package counter.items;

import counter.Countable;

import java.time.LocalDate;

public record Apple(Colour colour, LocalDate datePicked, LocalDate bestBefore) implements Countable {

    public int getCount() {
        return 1;
    }
}
