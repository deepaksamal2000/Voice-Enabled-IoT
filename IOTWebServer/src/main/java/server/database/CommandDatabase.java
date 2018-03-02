package server.database;

import server.database.entities.Command;
import server.database.entities.CommandSlot;
import server.database.entities.CommandVariation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandDatabase {
    private static CommandDatabase ourInstance = new CommandDatabase();
    public static CommandDatabase getInstance() {
        return ourInstance;
    }
    private CommandDatabase() {
    }

    private static String _commandTableName = "commands";
    private static String _commandViewName = "commandsview";
    private static String _commandId = "id";
    private static String _commandName = "name";
    private static String _commandVariation = "variation";
    private static String _commandSlot = "slot";

    public List<Command> getCommandList() {
        List<Command> commands = new ArrayList<Command>();
        List<Map<String, Object>> records = DatabaseManager.getInstance().queryDatabase("SELECT * from " + _commandTableName);

        for(Map<String, Object> record : records) {
            commands.add(new Command((Integer)record.get(_commandId), (String)record.get(_commandName)));
        }

        return commands;
    }

    public CommandVariation getCommandVariations(Command command) {
        CommandVariation commandVariation = new CommandVariation(command);

        String query = "SELECT " + _commandVariation + " FROM " + _commandViewName + " WHERE " + _commandName + "=\'" + command.getName() + "\'";
        List<Map<String, Object>> records = DatabaseManager.getInstance().queryDatabase(query);

        for(Map<String, Object> record : records) {
            commandVariation.getVariations().add((String)record.get(_commandVariation));
        }

        return commandVariation;
    }

    public CommandSlot getCommandSlot(Command command) {
        CommandSlot commandSlot = new CommandSlot(command);

        String query = "SELECT " + _commandSlot + " FROM " + _commandTableName + " WHERE " + _commandName + "=\'" + command.getName() + "\'";
        List<Map<String, Object>> records = DatabaseManager.getInstance().queryDatabase(query);

        for(Map<String, Object> record : records) {
            commandSlot.setSlotJson((String)record.get(_commandSlot));
        }

        return commandSlot;
    }

}
