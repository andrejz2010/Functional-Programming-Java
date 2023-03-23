package counter.items;

import counter.Countable;

public class Cart<T extends Countable> extends Box<Box<T>> {
}
