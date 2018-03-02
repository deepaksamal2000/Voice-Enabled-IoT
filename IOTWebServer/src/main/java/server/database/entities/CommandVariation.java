package server.database.entities;

import server.database.entities.Command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandVariation extends Command {
    private Pattern pattern = Pattern.compile("<(.*?)>");
    private List<String> variations = new ArrayList<String>();

    public CommandVariation(Command command) {
        super(command.getId(), command.getName());
    }

    public List<String> getVariations() {
        return variations;
    }

    public List<String> getSlots() {
        Set<String> slots = new HashSet<String>();

        for (String variation: variations) {
            Matcher matches = this.pattern.matcher(variation);
            while(matches.find()) {
                slots.add(matches.group().replaceAll("[<>]", ""));
            }
        }

        return new ArrayList<String>(slots);
    }

    public List<String> getSlots(String variation) {
        Set<String> slots = new HashSet<String>();

        Matcher matches = this.pattern.matcher(variation);
        while(matches.find()) {
            slots.add(matches.group().replaceAll("[<>]", ""));
        }

        return new ArrayList<String>(slots);
    }
}
