package server.nlu;

import server.algorithms.LCSS;
import server.database.CommandDatabase;
import server.database.entities.Command;
import server.database.entities.CommandVariation;
import server.database.entities.Device;
import server.nlu.entities.BestMatch;
import server.utils.StringHelper;

import java.util.*;

public class IntentRecognizer {
    private static IntentRecognizer ourInstance = new IntentRecognizer();
    public static IntentRecognizer getInstance() {
        return ourInstance;
    }
    private IntentRecognizer() {
    }

    public BestMatch processUtterance(String utterance) {
        BestMatch bestMatch = new BestMatch();

        //get the list of commands from the database
        List<Command> commands = CommandDatabase.getInstance().getCommandList();

        //tokenize the incoming utterance
        String[] utteranceTokens = StringHelper.tokenize(utterance);

        //for each of the command in the list, match to the variations
        for (Command command: commands) {
            //get the variations from the database
            CommandVariation commandVariation = CommandDatabase.getInstance().getCommandVariations(command);

            //premark the slot based on the usage
            List<String> slots = commandVariation.getSlots();

            //generate hypothesis about the various patterns matched with the slot
            //Map<String, boolean[]> slotPresence = TableLookUp.getInstance().generatePresence(command, slots, utteranceTokens);
            Map<String, Map<String, List<String>>> slotMatches = TableLookUp.getInstance().getSlotMatches(command, slots, utteranceTokens);


            //for each of the command variations, find the best match
            for (String variation: commandVariation.getVariations()) {

                //expand the variations based on the slot matches
                List<String> expandedVariations = this.expandVariations(commandVariation, variation, slotMatches);

                for (String eVariation : expandedVariations) {
                    //generate the tokens
                    String[] variationTokens = StringHelper.tokenize(eVariation);

                    /*
                     * This algorithm will be replaced with highly efficient Dynamic Time Warping (DTW).
                     * It will enable in better slot marking as compared to the LCSS by optimizing the
                     * substring sequence matching.
                     */
                    //Invoke the algorithm to compute the match score
                    LCSS<String> lcssAlgo = new LCSS<String>(utteranceTokens, variationTokens);
                    float score = lcssAlgo.computeMatchScore();

                    if(score > bestMatch.getScore()) {
                        bestMatch.setWinningCommand(command);
                        bestMatch.setScore(score);
                    }
                }

            }
        }

        return bestMatch;
    }

    private List<String> expandVariations(CommandVariation commandVariation, String variation, Map<String, Map<String, List<String>>> slotMatches) {
        List<String> variationalUtterances = new ArrayList<String>();

        //extract the slots from the variation
        List<String> slots = commandVariation.getSlots(variation);

        //extract the keywords
        List<String> keywords = Arrays.asList(StringHelper.extractKeywords(variation));

        Map<String, Set<String>> replacementSlots = new HashMap<String, Set<String>>();

        //for each of the slot, find the words which are not the keywords
        for (String slot : slots) {
            replacementSlots.put(slot, new HashSet<String>());

            for (Map.Entry<String, List<String>> match : slotMatches.get(slot).entrySet()) {
                if(keywords.contains(match.getKey()))
                    continue;

                replacementSlots.get(slot).addAll(match.getValue());
            }
        }

        //run algorithm to generate combinations of the matches
        


        return variationalUtterances;
    }
}
