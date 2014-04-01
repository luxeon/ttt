package com.tic.tac.toe.exception;

public class StepPropositionException extends Exception {

    public StepPropositionException(){
        super("Step proposition is not allowed");
    }

}
