package com.tic.tac.toy.service;

import com.tic.tac.toy.exception.StepHasBeenTakenException;
import com.tic.tac.toy.model.Game;
import com.tic.tac.toy.model.Step;

import java.util.List;

public interface GameService {

    Game findOne(Long id);
    List<Game> findAll();
    Game save(Game game);
    Step makeStep(Game game, Step step) throws StepHasBeenTakenException;
    boolean isStepPossible(Game game, int row, int col);
    boolean isGameFinished(Game game);
}
