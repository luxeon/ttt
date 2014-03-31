package com.tic.tac.toe.repository;

import com.tic.tac.toe.model.Game;
import com.tic.tac.toe.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    Step findByGameAndRowAndCol(Game game, int row, int col);
}
