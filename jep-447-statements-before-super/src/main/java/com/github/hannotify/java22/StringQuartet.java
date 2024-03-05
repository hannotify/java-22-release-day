package com.github.hannotify.java22;

import com.github.hannotify.Instrument;
import com.github.hannotify.Orchestra;

import java.util.List;

public class StringQuartet extends Orchestra {
    public StringQuartet(List<Instrument> instruments) {
        if (instruments.size() != 4) {
            throw new IllegalArgumentException("Not a quartet!");
        }

        super(instruments);
    }
}
