package com.tic.tac.toy.service;

import com.tic.tac.toy.GameStatus;
import com.tic.tac.toy.exception.StepHasBeenTakenException;
import com.tic.tac.toy.model.Game;
import com.tic.tac.toy.model.Step;
import com.tic.tac.toy.repository.GameRepository;
import com.tic.tac.toy.repository.StepRepository;
import com.tic.tac.toy.utils.WinnerFinder;
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
    public Step makeStep(Game game, Step step) throws StepHasBeenTakenException {
        if(!isStepPossible(game, step.getRow(), step.getCol())) {
            throw new StepHasBeenTakenException();
        }
        step.setGame(game);
        return stepRepository.save(step);
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
