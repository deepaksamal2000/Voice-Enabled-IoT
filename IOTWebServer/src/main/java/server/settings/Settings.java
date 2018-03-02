package server.settings;

import java.util.Properties;

public class Settings {
    private static Settings ourInstance = new Settings();
    public static Settings getInstance() {
        return ourInstance;
    }

    private Properties m_configFile;

    private Settings() {
        this.m_configFile = new java.util.Properties();
        try {
            this.m_configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream("config/settings.cfg"));
        }catch(Exception eta){
            eta.printStackTrace();
        }
    }

    public String getProperty(String key)
    {
        String value = this.m_configFile.getProperty(key);
        return value;
    }
}
