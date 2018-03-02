package server.nlu.entities;

import server.database.entities.Command;

import java.util.HashMap;
import java.util.Map;

public class BestMatch {
    private float score;
    private Command winningCommand;
    private Map<String, String> slotMap = new HashMap<String, String>();

    public Map<String, String> getSlotMap() {
        return slotMap;
    }

    public Command getWinningCommand() {
        return winningCommand;
    }

    public void setWinningCommand(Command winningCommand) {
        this.winningCommand = winningCommand;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}