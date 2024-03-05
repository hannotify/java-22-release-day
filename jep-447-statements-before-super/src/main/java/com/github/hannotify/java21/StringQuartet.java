package com.github.hannotify.java21;

import com.github.hannotify.Instrument;
import com.github.hannotify.Orchestra;

import java.util.List;

public class StringQuartet extends Orchestra {
    public StringQuartet(List<Instrument> instruments) {
        super(instruments); // Potentially unnecessary work!

        if (instruments.size() != 4) {
            throw new IllegalArgumentException("Not a quartet!");
        }
    }
}