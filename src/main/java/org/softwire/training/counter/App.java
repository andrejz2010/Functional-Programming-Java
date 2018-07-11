package org.softwire.training.counter;

import org.softwire.training.counter.items.Apple;
import org.softwire.training.counter.items.Box;
import org.softwire.training.counter.items.Cart;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] argv) {
        // Some things to count
        List<Apple> someApples = Arrays.asList(new Apple(), new Apple(), new Apple());

        Box<Apple> boxOfApples = new Box<>();
        boxOfApples.add(new Apple());
        boxOfApples.add(new Apple());

        Cart<Apple> cart = new Cart<>();
        cart.add(boxOfApples);

        // Some counters
        Counter<Apple> appleCounter = new Counter<>();
        someApples.forEach(appleCounter::add);

        System.out.println(appleCounter.getCount()); // Should be 3

        Counter<Cart<Apple>> cartCounter = new Counter<>();
        cartCounter.add(cart);

        System.out.println(cartCounter.getCount()); // Should be 2 (number of apples in the cart in total)

        Counter<Countable> anythingCounter = new Counter<>();
        someApples.forEach(anythingCounter::add);
        anythingCounter.add(cart);

        System.out.println(anythingCounter.getCount()); // Should be 5 - sum of the above
    }
}
