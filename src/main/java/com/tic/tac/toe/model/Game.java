package com.tic.tac.toe.model;

import com.tic.tac.toe.GameStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany
    private List<Step> steps;

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
}
