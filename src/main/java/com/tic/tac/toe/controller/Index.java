package com.tic.tac.toe.controller;

import com.tic.tac.toe.Constants;
import com.tic.tac.toe.GameStatus;
import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;
import com.tic.tac.toe.service.ComputerService;
import com.tic.tac.toe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Index {

    @Autowired
    private GameService gameService;

    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return getIndexModelAndView(new Game());
    }

    private ModelAndView getIndexModelAndView(Game game) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("games", gameService.findAll());
        modelAndView.addObject("newGame", game);
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

    @RequestMapping(value = "/game/{game}", method = RequestMethod.GET)
    public ModelAndView game(@PathVariable Game game) {
        ModelAndView modelAndView = new ModelAndView("game");
        modelAndView.addObject("game", game);
        modelAndView.addObject("board", game.asBoard());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/make-step", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> makeStep(@RequestParam Game game, int row, int col) {
        Map<String, Object> result = new HashMap<>();
        try {
            gameService.makeStep(game, new Step(Constants.PLAYER_SIGN, row, col));
            if(gameService.isGameFinished(game)) {
                putFinishedGameData(game, result);
            } else {
                Step step = computerService.proposeStep(game);
                gameService.makeStep(game, step);
                if(gameService.isGameFinished(game)) {
                    putFinishedGameData(game, result);
                    result.put("computer", step);
                } else {
                    result.put("finished", false);
                    result.put("success", true);
                    result.put("computer", step);
                }
            }

        } catch (Exception e) {
            result.put("finished", false);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        return result;
    }

    private void putFinishedGameData(Game game, Map<String, Object> result) {
        result.put("finished", true);
        result.put("success", true);
        result.put("status", game.getStatus());
    }
}
