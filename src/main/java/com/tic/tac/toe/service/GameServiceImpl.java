package com.tic.tac.toe.service;

import com.tic.tac.toe.GameStatus;
import com.tic.tac.toe.exception.StepHasBeenTakenException;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;
import com.tic.tac.toe.repository.GameRepository;
import com.tic.tac.toe.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private WinnerFinder winnerFinder;

    @Override
    public Game findOne(Long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void makeStep(Game game, Step step) throws StepHasBeenTakenException {
        if(!isStepPossible(game, step.getRow(), step.getCol())) {
            throw new StepHasBeenTakenException();
        }
        step.setGame(game);
        game.getSteps().add(step);
        gameRepository.save(game);
    }

    @Override
    public boolean isStepPossible(Game game, int row, int col) {
        return stepRepository.findByGameAndRowAndCol(game, row, col) == null;
    }

    @Override
    public boolean isGameFinished(Game game) {
        GameStatus status = winnerFinder.find(game);
        if(status == null) {
            return false;
        }
        game.setStatus(status);
        gameRepository.save(game);
        return true;
    }
}
