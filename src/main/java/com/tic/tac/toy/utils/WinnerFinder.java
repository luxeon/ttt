package com.tic.tac.toy.utils;

import com.tic.tac.toy.GameStatus;
import com.tic.tac.toy.model.Game;

public interface WinnerFinder {

    GameStatus find(Game game);

}
