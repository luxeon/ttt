package com.tic.tac.toe.convertor;

import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class GameConvertor implements Converter<Long, Game>{

    @Autowired
    private GameService gameService;

    @Override
    public Game convert(Long id) {
        return gameService.findOne(id);
    }
}
