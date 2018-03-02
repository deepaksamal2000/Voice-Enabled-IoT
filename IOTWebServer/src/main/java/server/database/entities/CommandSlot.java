package server.database.entities;

import java.util.Map;

public class CommandSlot extends Command {
    private String slotJson = new String();

    public CommandSlot(Command command) {
        super(command.getId(), command.getName());
    }

    public String getSlotJson() {
        return slotJson;
    }

    public void setSlotJson(String slotJson) {
        this.slotJson = new String(slotJson);
    }

    public void processTokens(Map<String, String> slotMaps) {
        //iterate over each of the map entry and replace it in the json
        for(Map.Entry<String, String> slot : slotMaps.entrySet()) {
            slotJson = slotJson.replace(slot.getKey(), slot.getValue());
        }
    }
}
