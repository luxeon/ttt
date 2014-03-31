package com.tic.tac.toy.service;

import com.tic.tac.toy.exception.StepPropositionException;
import com.tic.tac.toy.model.Game;
import com.tic.tac.toy.model.Step;

public interface ComputerService {

    Step proposeStep(Game game) throws StepPropositionException;
}
