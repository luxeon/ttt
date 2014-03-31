package com.tic.tac.toy.controller;

import com.tic.tac.toy.GameStatus;
import com.tic.tac.toy.model.Game;
import com.tic.tac.toy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class Index {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return getIndexModelAndView(new Game());
    }

    private ModelAndView getIndexModelAndView(Game game) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("games", gameService.findAll());
        modelAndView.addObject("game", game);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView createGame(@Valid Game game, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return getIndexModelAndView(game);
        }
        game.setStatus(GameStatus.IN_PROGRESS);
        game = gameService.save(game);
        return new ModelAndView("redirect:/game/" + game.getId());
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public ModelAndView game(Game game) {
        return new ModelAndView("game", "game", game);
    }
}
