package com.tic.tac.toe.model;

import com.tic.tac.toe.Constants;
import com.tic.tac.toe.Sign;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Step {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Game game;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sign sign;

    @Min(Constants.GAME_FIRST_ROW)
    @Max(Constants.GAME_TOTAL_ROWS)
    private int row;

    @Min(Constants.GAME_FIRST_ROW)
    @Max(Constants.GAME_TOTAL_ROWS)
    private int col;

    public Step(){}

    public Step(Sign sign, int row, int col) {
        this.sign = sign;
        this.row = row;
        this.col = col;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (col != step.col) return false;
        if (row != step.row) return false;
        if (game != null ? !game.equals(step.game) : step.game != null) return false;
        if (sign != step.sign) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + row;
        result = 31 * result + col;
        return result;
    }
}
