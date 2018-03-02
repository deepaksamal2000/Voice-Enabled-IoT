import com.google.gson.GsonBuilder;
import server.database.CommandDatabase;
import server.database.entities.Command;
import server.database.entities.CommandVariation;

import java.util.List;

public class DatabaseAccess {

    public static void main(String[] args) {
        List<Command> commands = CommandDatabase.getInstance().getCommandList();
        System.out.println(new GsonBuilder().create().toJson(commands));

        CommandVariation variation = CommandDatabase.getInstance().getCommandVariations(commands.get(1));
        System.out.println(new GsonBuilder().create().toJson(variation));
    }

}
