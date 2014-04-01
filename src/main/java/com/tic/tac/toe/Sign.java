package com.tic.tac.toe;

public enum Sign {

    CROSS("X"), ZERO("O");

    private final String symbol;

    Sign(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
