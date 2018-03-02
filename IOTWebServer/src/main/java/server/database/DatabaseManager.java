package server.database;

import server.settings.DatabaseSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private static DatabaseManager ourInstance = new DatabaseManager();
    public static DatabaseManager getInstance() {
        return ourInstance;
    }

    private Connection m_dbConnection;

    private DatabaseManager() {

        try {
            Class.forName("org.postgresql.Driver");
            String serviceURI = "jdbc:postgresql://" + DatabaseSettings.getInstance().getServerIP() + ":" +
                    DatabaseSettings.getInstance().getServerPort() + "/" + DatabaseSettings.getInstance().getDatabaseName();
            this.m_dbConnection  = DriverManager.getConnection(serviceURI,DatabaseSettings.getInstance().getDatabaseUser(),"");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(this.m_dbConnection != null)
            System.out.println("Database Connection is successful.");
    }

    public List<Map<String, Object>> queryDatabase(String queryStatement) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if(this.m_dbConnection != null) {
            try {
                ResultSet resultSet = this.m_dbConnection.prepareStatement(queryStatement).executeQuery();
                ResultSetMetaData metadata = resultSet.getMetaData();

                while (resultSet.next()) {
                    Map<String, Object> record = new HashMap<String, Object>();

                    for(int count = 1; count <= metadata.getColumnCount(); count++) {
                        record.put(metadata.getColumnName(count), resultSet.getObject(count));
                    }

                    result.add(record);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
