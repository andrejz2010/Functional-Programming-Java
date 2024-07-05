package counter;

import counter.items.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class App {

    private static void printApples(List<Apple> appleList, Comparator<Apple> appleComparator) {
        appleList.stream()
                .sorted(appleComparator)
                .forEach(System.out::println);
    }

    public static void main(String[] argv) {
        // Some things to count
        List<Apple> someApples = Arrays.asList(
                new Apple(Colour.RED, LocalDate.of(2023, 3, 8), LocalDate.of(2023, 5, 4)),
                new Apple(Colour.RED, LocalDate.of(2023, 2, 10), LocalDate.of(2023, 6, 20)),
                new Apple(Colour.RED, LocalDate.of(2023, 1, 7), LocalDate.of(2023, 4, 18)),
                new Apple(Colour.YELLOW, LocalDate.of(2023, 3, 25), LocalDate.of(2023, 5, 11)),
                new Apple(Colour.YELLOW, LocalDate.of(2023, 2, 23), LocalDate.of(2023, 4, 16)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 2, 12), LocalDate.of(2023, 3, 7)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 2, 9), LocalDate.of(2023, 5, 9)),
                new Apple(Colour.GREEN, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 4, 10))
        );

        Box<Apple> boxOfApples = new Box<>();
        boxOfApples.add(new Apple(Colour.RED, LocalDate.of(2023, 3, 8), LocalDate.of(2023, 5, 4)));
        boxOfApples.add(new Apple(Colour.YELLOW, LocalDate.of(2023, 2, 23), LocalDate.of(2023, 4, 16)));

        Cart<Apple> cart = new Cart<>();
        cart.add(boxOfApples);

        System.out.println("Lambda Exercise Output:");

        // Lambda Exercises
        someApples.sort(Comparator.comparing(Apple::bestBefore).reversed());
        someApples.sort(Comparator.comparing(Apple::bestBefore, Comparator.reverseOrder()));

        someApples.forEach(System.out::println);

        Comparator<Apple> datePickedComparator = Comparator.comparing(Apple::datePicked).reversed();
        Comparator<Apple> bestBeforeComparator = Comparator.comparing(Apple::bestBefore).reversed();
        Comparator<Apple> colourComparator = Comparator.comparing(apple -> apple.colour().toString());

        printApples(someApples, colourComparator);

        System.out.println("Streams Exercises Output:");

        // Streams Exercises
        someApples.forEach(System.out::println);

        someApples.stream()
                .skip(3)
                .forEach(System.out::println);

        someApples.stream()
                .findFirst()
                .ifPresent(System.out::println);

        someApples.stream()
                .filter(apple -> apple.bestBefore().isBefore(LocalDate.of(2023, 4, 15)))
                .forEach(System.out::println);

        someApples.stream()
                .filter(apple -> apple.bestBefore().isBefore(LocalDate.of(2023, 4, 15)))
                .forEach(apple -> System.out.println(String.format("There is a %s apple that is best before %s", apple.colour(), apple.bestBefore())));

        someApples.stream()
                .filter(apple -> apple.colour().equals(Colour.RED))
                .forEach(apple -> System.out.println(String.format("There is a %s apple that is best before %s", apple.colour(), apple.bestBefore())));

        someApples.stream()
                .skip(3)
                .sorted(datePickedComparator)
                .map(Apple::colour)
                .forEach(System.out::println);

        someApples.stream()
                .filter(apple -> apple.colour().toString().contains("e"))
                .forEach(apple -> System.out.println(String.format("There is a %s apple that is best before %s", apple.colour(), apple.bestBefore())));

        long count = someApples.stream()
                .filter(apple -> apple.datePicked().isAfter(LocalDate.of(2023, 4, 12)))
                .count();
        System.out.println(count);

        System.out.println("Predicate Exercises Output:");
        Counter<Apple> appleCounter = new Counter<>();
        someApples.forEach(appleCounter::add);
        System.out.println("Below should be 8");
        System.out.println(appleCounter.getCount()); // Should be 8

        Counter<Apple> allAppleCounter = new Counter<>();
        someApples.forEach(allAppleCounter::add);
        System.out.println("for all apples " + allAppleCounter.getCount());

        Counter<Apple> redApples = new Counter<>(apple -> apple.colour().equals(Colour.RED));
        someApples.forEach(redApples::add);
        System.out.println("for red apples " + redApples.getCount());

        Counter<Cart<Apple>> cartCounter = new Counter<>();
        cartCounter.add(cart);

        System.out.println("Below should be 2");
        System.out.println(cartCounter.getCount()); // Should be 2 (number of apples in the cart in total)

        Counter<Countable> anythingCounter = new Counter<>();
        someApples.forEach(anythingCounter::add);
        anythingCounter.add(cart);

        System.out.println("Below should be 10");
        System.out.println(anythingCounter.getCount()); // Should be 10 - sum of the above
    }
}