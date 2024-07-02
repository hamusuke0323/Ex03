package com.hamusuke.headsortails;

public enum Result {
    HEADS("Heads"),
    TAILS("Tails");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public static Result getResult(boolean flag) {
        return flag ? HEADS : TAILS;
    }

    public String getName() {
        return this.name;
    }
}
