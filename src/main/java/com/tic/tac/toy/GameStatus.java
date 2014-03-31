package com.tic.tac.toy;

public enum GameStatus {

    IN_PROGRESS, X_WON, O_WON, DRAW;

    public static GameStatus getStatusBySign(Sign sign) {
        if(sign == Sign.CROSS) {
            return X_WON;
        }
        return O_WON;
    }

}
