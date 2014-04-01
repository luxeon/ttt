package com.tic.tac.toe.exception;

public class StepHasBeenTakenException extends Exception {

    public StepHasBeenTakenException() {
        super("Step has been taken");
    }

}
