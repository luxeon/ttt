package com.tic.tac.toy.convertor;

import com.tic.tac.toy.model.Game;
import com.tic.tac.toy.service.GameService;
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
