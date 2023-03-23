package counter;

public class Counter<T extends Countable> implements Countable {
    private int count;

    public int getCount() {
        return count;
    }

    public void add(T item) {
        count += item.getCount();
    }
}
