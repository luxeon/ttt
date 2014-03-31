package com.tic.tac.toe.service;

import com.tic.tac.toe.Constants;
import com.tic.tac.toe.exception.StepPropositionException;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private GameService gameService;

    @Override
    public Step proposeStep(Game game) throws StepPropositionException {
        // поиск шага влоб, без какой-либо оптимизации или логики
        for(int i = Constants.GAME_FIRST_ROW; i < Constants.GAME_TOTAL_ROWS; i++) {
            for(int j = Constants.GAME_FIRST_ROW; j < Constants.GAME_TOTAL_ROWS; j++) {
                if(gameService.isStepPossible(game, i, j)) {
                    return new Step(Constants.COMPUTER_SIGN, i, j);
                }
            }
        }
        throw new StepPropositionException();
    }
}
