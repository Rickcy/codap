package com.company.entity;

import java.util.Arrays;

public enum AuctionDirection {
    BUY('B'), SELL('S');

    private final char direction;

    AuctionDirection(char direction) {
        this.direction = direction;
    }

    public static AuctionDirection create(char direction) {
        return Arrays.stream(values())
                .filter(d -> d.direction == direction)
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return String.valueOf(this.direction);
    }

    public char getValue() {
        return this.direction;
    }
}
