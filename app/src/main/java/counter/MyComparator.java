package counter;

import counter.items.Apple;

import java.util.Comparator;

public class MyComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple current, Apple next) {
        if (current.bestBefore().isBefore(next.bestBefore())) {
            return 1;
        }
        else if (current.bestBefore().isEqual(next.bestBefore())) {
            return 0;
        }
        return -1;
    }
}