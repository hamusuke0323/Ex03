package com.hamusuke.headsortails;

public record Round(int num, Result result) {
    @Override
    public String toString() {
        return "Round %d: %s".formatted(this.num, this.result.getName());
    }
}
