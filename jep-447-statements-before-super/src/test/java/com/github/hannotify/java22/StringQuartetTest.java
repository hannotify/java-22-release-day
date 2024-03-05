package com.github.hannotify.java22;

import com.github.hannotify.Instrument;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StringQuartetTest {
    @Test
    void testStatementsBeforeSuper() {
        assertThrows(IllegalArgumentException.class, () -> {
            var stringQuartet = new StringQuartet(List.of(
                    new Instrument("Violin"),
                    new Instrument("Cello"),
                    new Instrument("Contra bass")
            ));
        });
    }
}