package com.tic.tac.toe.utils;

import com.tic.tac.toe.Constants;
import com.tic.tac.toe.GameStatus;
import com.tic.tac.toe.Sign;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WinnerFinderImpl implements WinnerFinder {

    /*
    * тут я не особо парился с реализацией и наведением красоты - в нормальном проекте просто можно будет подменить реализацию
    * */

    @Override
    public GameStatus find(Game game) {
        List<Step> steps = game.getSteps();
        if(steps.size() < Constants.GAME_TOTAL_ROWS - 1) {
            return null;
        }

        if(steps.size() == Constants.GAME_TOTAL_ROWS * Constants.GAME_TOTAL_ROWS) {
            return GameStatus.DRAW;
        }

        Sign sign = findSignOnBoard(steps);

        if(sign != null) {
            return GameStatus.getStatusBySign(sign);
        }
        return null;
    }

    private Sign findSignOnBoard(List<Step> steps) {
        Sign[][] board = getBoardFromSteps(steps);

        Sign sign = findSign(board, true);
        if(sign == null) {
            sign = findSign(board, false);
            if(sign == null) {
                sign = findSignInDiagonal(board, true);
                if(sign == null) {
                    sign = findSignInDiagonal(board, false);
                }
            }
        }
        return sign;
    }

    private Sign findSignInDiagonal(Sign[][] board, boolean left) {
        int crosses = 0,
                zeroes = 0;
        Sign sign;
        for(int i = Constants.GAME_FIRST_ROW; i < Constants.GAME_TOTAL_ROWS; i++) {
            if(left) {
                sign = board[i][i];
            } else {
                sign = board[Constants.GAME_TOTAL_ROWS - i - 1][Constants.GAME_TOTAL_ROWS - i - 1];
            }
            if(sign == null) {
                return null;
            }
            if(sign == Sign.CROSS) {
                crosses++;
            } else {
                zeroes++;
            }
            if(crosses > 0 && zeroes > 0) {
                return null;
            }
        }
        if(crosses == Constants.GAME_TOTAL_ROWS) {
            return Sign.CROSS;
        }
        if(zeroes == Constants.GAME_TOTAL_ROWS) {
            return Sign.ZERO;
        }
        return null;
    }

    private Sign findSign(Sign[][] board, boolean horizontal) {
        int crosses = 0,
                zeroes = 0;
        Sign sign;
        for(int i = Constants.GAME_FIRST_ROW; i < Constants.GAME_TOTAL_ROWS; i++) {
            for(int j = Constants.GAME_FIRST_ROW; j < Constants.GAME_TOTAL_ROWS; j++) {
                if(horizontal) {
                    sign = board[i][j];
                } else {
                    sign = board[j][i];
                }
                if(sign != null) {
                    if(sign == Sign.CROSS) {
                        crosses++;
                    } else {
                        zeroes++;
                    }
                }
                if(sign == null || (crosses > 0 && zeroes > 0)) {
                    break;
                }
                if(crosses == Constants.GAME_TOTAL_ROWS) {
                    return Sign.CROSS;
                }
                if(zeroes == Constants.GAME_TOTAL_ROWS) {
                    return Sign.ZERO;
                }
            }
            crosses = 0;
            zeroes = 0;
        }
        return null;
    }

    private Sign[][] getBoardFromSteps(List<Step> steps) {
        Sign[][] board = new Sign[Constants.GAME_TOTAL_ROWS][Constants.GAME_TOTAL_ROWS];
        for (Step step : steps) {
            board[step.getRow()][step.getCol()] = step.getSign();
        }
        return board;
    }
}
