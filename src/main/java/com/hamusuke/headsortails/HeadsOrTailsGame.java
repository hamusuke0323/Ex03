package com.hamusuke.headsortails;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class HeadsOrTailsGame {
    private final int roundNum;
    private final Random random = new Random();
    private final Map<Result, AtomicInteger> resultCounter = makeResultMap(AtomicInteger::new);

    public HeadsOrTailsGame(int roundNum) {
        this.roundNum = roundNum;
    }

    private static <V> EnumMap<Result, V> makeResultMap(Supplier<V> initializer) {
        EnumMap<Result, V> map = new EnumMap<>(Result.class);
        for (var key : Result.class.getEnumConstants()) {
            map.put(key, initializer.get());
        }

        return map;
    }

    public void start() {
        System.out.println("Tossing a coin...");
        this.imitate();
        this.printResults();
        this.printWonOrLost();
    }

    private void imitate() {
        for (int i = 0; i < this.roundNum; i++) {
            var round = this.toss(i + 1);
            this.saveRound(round);

            System.out.println(round);
        }
    }

    private Round toss(int curRoundNum) {
        return new Round(curRoundNum, Result.getResult(this.random.nextBoolean()));
    }

    private void saveRound(Round round) {
        this.resultCounter.get(round.result()).incrementAndGet();
    }

    private void printResults() {
        System.out.println(
                this.toResultString(Result.HEADS) + ", " + this.toResultString(Result.TAILS));
    }

    private int getCount(Result result) {
        return this.resultCounter.get(result).get();
    }

    private String toResultString(Result result) {
        return result.getName() + ": " + this.getCount(result);
    }

    private void printWonOrLost() {
        int heads = this.getCount(Result.HEADS);
        int tails = this.getCount(Result.TAILS);

        System.out.println("You " + (heads > tails ? "won" : "lost") + "!");
    }
}
