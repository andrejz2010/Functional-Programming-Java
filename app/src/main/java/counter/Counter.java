package counter;

import java.util.function.Predicate;

public class Counter<T extends Countable> implements Countable {
    private int count;
    private Predicate<T> predicate;

    public Counter() {
        this.predicate = (item) -> true;
    }

    public Counter(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public int getCount() {
        return count;
    }

    public void add(T item) {
        if (predicate.test(item)) {
            count += item.getCount();
        }
    }
}