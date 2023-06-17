package ru.otus.interpreter.utils;

import static java.util.Objects.isNull;

public class Checker {

    public static boolean isNullOrBlank(String s) {
        if (isNull(s)) {
            return true;
        } else return s.isBlank();
    }
}
