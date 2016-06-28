package com.applet.test;

/**
 * Created by 1 on 28.06.2016.
 */
public enum Difficult {
    SIMPLE(12),
    MIDDLE(16),
    HARD(30);

    private final int cardCount;

    Difficult(int cardCount) {
        this.cardCount = cardCount;
    }

    public int getCardCount() {
        return cardCount;
    }
}
