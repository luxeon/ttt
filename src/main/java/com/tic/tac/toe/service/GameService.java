package com.tic.tac.toe.service;

import com.tic.tac.toe.exception.StepHasBeenTakenException;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;

import java.util.List;

public interface GameService {

    Game findOne(Long id);
    List<Game> findAll();
    Game save(Game game);
    Step makeStep(Game game, Step step) throws StepHasBeenTakenException;
    boolean isStepPossible(Game game, int row, int col);
    boolean isGameFinished(Game game);
}
