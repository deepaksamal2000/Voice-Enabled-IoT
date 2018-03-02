package server.settings;

public class DatabaseSettings {
    private static DatabaseSettings ourInstance = new DatabaseSettings();
    public static DatabaseSettings getInstance() {
        return ourInstance;
    }

    private DatabaseSettings() {
    }

    public String getServerIP() {
        return Settings.getInstance().getProperty("DBServerIP");
    }

    public String getServerPort() {
        return Settings.getInstance().getProperty("DBServerPort");
    }

    public String getDatabaseName() {
        return Settings.getInstance().getProperty("DBName");
    }

    public String getDatabaseUser() {
        return Settings.getInstance().getProperty("DBUserName");
    }

    public String getDatabasePassword() {
        return Settings.getInstance().getProperty("DBPassword");
    }
}
