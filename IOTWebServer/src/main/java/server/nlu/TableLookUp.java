package server.nlu;

import server.database.DatabaseManager;
import server.database.HandlerDatabase;
import server.database.entities.Command;
import server.database.entities.Handler;
import server.database.queries.QueryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableLookUp {
    private static TableLookUp ourInstance = new TableLookUp();
    public static TableLookUp getInstance() {
        return ourInstance;
    }
    private TableLookUp() {
    }

    public Map<String, boolean[]> generatePresence(Command command, List<String> slots, String[] utteranceTokens){
        Map<String, boolean[]> slotPresenceMap = new HashMap<String, boolean[]>();

        //for each of the slot get the handler details
        for (String slot: slots) {
            Handler handler = HandlerDatabase.getInstance().getHandler(command, slot);
            boolean presence[] = new boolean[utteranceTokens.length];

            for (int i = 0; i < utteranceTokens.length; i++) {
                //foreach of the handler, find the db records
                String slotMatchRecordsQuery = QueryRepository.makeSlotDetectionQuery(handler, utteranceTokens[i]);
                List<Map<String, Object>> matchedRecords = DatabaseManager.getInstance().queryDatabase(slotMatchRecordsQuery);
                presence[i] = (matchedRecords.size() == 0)? false : true;
            }

            slotPresenceMap.put(slot, presence);
        }

        return slotPresenceMap;
    }

    public Map<String, Map<String, List<String>>> getSlotMatches(Command command, List<String> slots, String[] utteranceTokens){
        Map<String, Map<String, List<String>>> slotPresenceMap = new HashMap<String, Map<String, List<String>>>();

        //for each of the slot get the handler details
        for (String slot: slots) {
            Handler handler = HandlerDatabase.getInstance().getHandler(command, slot);
            Map<String, List<String>> tokenMaps = new HashMap<String, List<String>>();

            for (int i = 0; i < utteranceTokens.length; i++) {
                //foreach of the handler, find the db records
                String slotMatchRecordsQuery = QueryRepository.makeSlotDetectionQuery(handler, utteranceTokens[i]);
                List<Map<String, Object>> matchedRecords = DatabaseManager.getInstance().queryDatabase(slotMatchRecordsQuery);

                List<String> matchedNames = new ArrayList<String>();
                for (Map<String, Object> record : matchedRecords) {
                    matchedNames.add((String)record.get(handler.getColumnName()));
                }

                tokenMaps.put(utteranceTokens[i], matchedNames);
            }

            slotPresenceMap.put(slot, tokenMaps);
        }

        return slotPresenceMap;
    }
}
