package server.nlu;

import server.database.entities.Command;
import server.database.entities.CommandSlot;
import server.nlu.entities.BestMatch;
import server.nlu.entities.Context;

import java.util.HashMap;
import java.util.Map;

public class NLU {
    private static NLU ourInstance = new NLU();
    public static NLU getInstance() {
        return ourInstance;
    }

    private NLU() {
    }

    public String processUtterance(String utterance) {
        //normalize the utterance
        String normalizedUtterance = this.normalize(utterance);

        //once the text is normalized, identify the intent of the utterance
        //get the command name & the slot json
        BestMatch matchedIntent = IntentRecognizer.getInstance().processUtterance(normalizedUtterance);

        //using the best match information, trigger the command handler
        CommandSlot bestCommandSlot = new CommandSlot(matchedIntent.getWinningCommand());
        bestCommandSlot.processTokens(matchedIntent.getSlotMap());

        //propagate the command slot to the module which can process the command
        Context context = new Context(bestCommandSlot);
        String response = CommandExecutor.getInstance().processCommand(context);

        return response;
    }

    private String normalize(String utterance) {
        StringBuilder nromalizedText = new StringBuilder(utterance.toLowerCase());

        return nromalizedText.toString();
    }
}
