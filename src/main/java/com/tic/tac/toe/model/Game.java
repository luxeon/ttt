package com.tic.tac.toe.model;

import com.tic.tac.toe.GameStatus;
import com.tic.tac.toe.Sign;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Game implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Step> steps = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Map<String, Sign> asBoard() {
        Map<String, Sign> board = new HashMap<>(steps.size());
        for (Step step : steps) {
            board.put(step.getRow() + "_" + step.getCol(), step.getSign());
        }
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (name != null ? !name.equals(game.name) : game.name != null) return false;
        if (status != game.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
