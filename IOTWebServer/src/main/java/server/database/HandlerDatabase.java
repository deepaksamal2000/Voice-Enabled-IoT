package server.database;

import server.database.entities.Command;
import server.database.entities.Handler;

import java.util.List;
import java.util.Map;

public class HandlerDatabase {
    private static HandlerDatabase ourInstance = new HandlerDatabase();
    public static HandlerDatabase getInstance() {
        return ourInstance;
    }
    private HandlerDatabase() {
    }

    private static String _handlerTableName = "handlers";
    private static String _handlerName = "handler";
    private static String _tableNameColumn = "table_name";
    private static String _columnNameColumn = "column_name";
    private static String _commandId = "id";

    public Handler getHandler(Command command, String handlerName) {
        Handler handler = new Handler();

        //set the command id
        handler.setCommandId(command.getId());

        //set the handler name
        handler.setHandlerName(handlerName);

        //query the table for the given command id and handler name
        String query = "SELECT * FROM " + _handlerTableName + " WHERE " + _handlerName + "=\'" + handlerName + "\' AND " + _commandId + "=" + command.getId();
        List<Map<String, Object>> records = DatabaseManager.getInstance().queryDatabase(query);

        for(Map<String, Object> record : records) {
            handler.setColumnName((String)record.get(_columnNameColumn));
            handler.setTableName((String)record.get(_tableNameColumn));
        }

        return handler;
    }
}
