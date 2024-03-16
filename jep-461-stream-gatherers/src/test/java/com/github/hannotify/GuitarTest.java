package com.github.hannotify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;

import static org.junit.jupiter.api.Assertions.*;

class GuitarTest {
    List<Guitar> guitars = List.of(
            new Guitar("Cordoba F7 Paco Flamenco", GuitarStyle.CLASSICAL),
            new Guitar("Taylor GS Mini-e Koa", GuitarStyle.WESTERN),
            new Guitar("Gibson Les Paul", GuitarStyle.ELECTRIC),
            new Guitar("Fender Stratocaster", GuitarStyle.ELECTRIC));
    @Test
    void testStandardStream() {
        long numberOfNonClassicalGuitars = guitars.stream() // source of elements
                .filter(g -> GuitarStyle.CLASSICAL != g.guitarStyle()) // intermediate operation
                .collect(Collectors.counting()); // terminal operation
    }

    @Test
    void testCustomIntermediateOperation() {
        guitars.stream()
                .gather(distinctBy(Guitar::guitarStyle))
                .forEach(System.out::println);
    }

    static <T, A> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends A> classifier) {
        Supplier<Map<A, List<T>>> initializer = HashMap::new;
        Gatherer.Integrator<Map<A, List<T>>, T, T> integrator = (state, element, _) -> {
            state.computeIfAbsent(classifier.apply(element), _ -> new ArrayList<>()).add(element);
            return true; // true, because more elements need to be consumed
        };
        BiConsumer<Map<A, List<T>>, Gatherer.Downstream<? super T>> finisher = (state, downstream) -> {
            state.forEach((_, value) -> downstream.push(value.getLast()));
        };
        return Gatherer.ofSequential(initializer, integrator, finisher);
    }
}