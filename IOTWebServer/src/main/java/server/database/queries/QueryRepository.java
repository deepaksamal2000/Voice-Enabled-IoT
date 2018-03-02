package server.database.queries;

import server.database.entities.Handler;

public class QueryRepository {
    public static String makeSlotDetectionQuery(Handler handler, String utteranceTokens) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM " + handler.getTableName() + " WHERE " +
                handler.getColumnName() + " like \'%" + utteranceTokens + "%\'");

        return query.toString();
    }
}
