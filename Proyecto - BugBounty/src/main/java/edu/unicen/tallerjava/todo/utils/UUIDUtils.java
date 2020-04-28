package edu.unicen.tallerjava.todo.utils;

import java.util.UUID;

public class UUIDUtils {
    public static UUID simple(int i) {
        return new UUID(i, 0);
    }
}
