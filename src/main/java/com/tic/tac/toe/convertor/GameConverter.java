package com.tic.tac.toe.convertor;

import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class GameConverter implements Converter<String, Game>{

    @Autowired
    private GameService gameService;

    @Override
    public Game convert(String game) {
        try {
            return gameService.findOne(Long.valueOf(game));
        } catch (Exception e) {
            return null;
        }
    }
}
