package com.example.bowling.validators;

import com.example.bowling.models.Frame;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RollValidator {

    public String isValidatePin(int pin, List<Frame> frames) {if(isGameOver(frames)){
            return "Game is over";
        }
        if(pin < 0 || pin > 10){
            return "Invalid pin number";
        }
        return "";
    }

    boolean isGameOver(final List<Frame> frames){
        if(frames.size() == 10){
            Frame frame = frames.get(9);
            if(frame.isStrike() && frame.getBonusRoll1()!= -1 && frame.getBonusRoll2()!=-1){
                return true;
            }else return frame.isSpare() && frame.getBonusRoll1() != -1;
        }
        return false;
    }
}
