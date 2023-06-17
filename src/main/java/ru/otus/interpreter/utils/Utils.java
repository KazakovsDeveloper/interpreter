package ru.otus.interpreter.utils;

import java.util.UUID;

import static java.util.Objects.isNull;

public class Utils {

    public static boolean isNullOrBlank(String s) {
        if (isNull(s)) {
            return true;
        } else return s.isBlank();
    }

    public static String createRandomId() {
        return UUID.randomUUID().toString();
    }
}
