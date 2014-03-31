package com.tic.tac.toe.utils;

import com.tic.tac.toe.GameStatus;
import com.tic.tac.toe.model.Game;

public interface WinnerFinder {

    GameStatus find(Game game);

}
