package com.tic.tac.toe.service;

import com.tic.tac.toe.exception.StepPropositionException;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;

public interface ComputerService {

    Step proposeStep(Game game) throws StepPropositionException;
}
