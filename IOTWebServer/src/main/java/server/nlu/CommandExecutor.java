package server.nlu;

import server.nlu.entities.Context;

public class CommandExecutor {
    private static CommandExecutor ourInstance = new CommandExecutor();
    public static CommandExecutor getInstance() {
        return ourInstance;
    }
    private CommandExecutor() {
    }

    public String processCommand(Context context) {

        //for the incoming command, execute the handler for the command

        return "";
    }
}
