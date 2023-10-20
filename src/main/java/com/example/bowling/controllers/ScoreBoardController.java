package com.example.bowling.controllers;

import com.example.bowling.controllers.exceptionHandlers.ValidationException;
import com.example.bowling.data.ScoreBoardData;
import com.example.bowling.models.UserGame;
import com.example.bowling.services.scoreboard.ScoreBoardCalculationService;
import com.example.bowling.validators.RollValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/score")
public class ScoreBoardController {

    private final ScoreBoardCalculationService scoreBoardCalculationService;
    private final RollValidator rollValidator;

    @Autowired
    public ScoreBoardController(final ScoreBoardCalculationService scoreBoardCalculationService, RollValidator rollValidator) {
        this.scoreBoardCalculationService = scoreBoardCalculationService;
        this.rollValidator = rollValidator;
    }

    @GetMapping("/scoreboard/{user}")
    public ResponseEntity<ScoreBoardData> getScoreboard(@PathVariable("user") String username) {
        ScoreBoardData scoreboardData = scoreBoardCalculationService.calculateScoreboardForUser(username);
        return new ResponseEntity<>(scoreboardData, HttpStatus.OK);
    }

    @PostMapping("/{user}/{roll}")
    public void addRoll(@PathVariable("user") final String userName, @PathVariable("roll") final int roll) {
       String errorMessage = rollValidator.isValidatePin(roll, UserGame.getFrames(userName));
        if (errorMessage.isEmpty()) {
            scoreBoardCalculationService.addRollForUser(userName, roll);
        }else{
            throw new ValidationException(errorMessage);
        }

    }
}
