package server.nlu.entities;

import server.database.entities.CommandSlot;

public class Context {
    private CommandSlot targetCommand;

    public Context(CommandSlot targetCommand) {
        this.targetCommand = targetCommand;
    }

    public CommandSlot getTargetCommand() {
        return targetCommand;
    }

    public void setTargetCommand(CommandSlot targetCommand) {
        this.targetCommand = targetCommand;
    }
}
