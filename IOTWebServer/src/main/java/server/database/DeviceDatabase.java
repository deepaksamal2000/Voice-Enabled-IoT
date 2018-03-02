package server.database;


import server.database.entities.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceDatabase {
    private static DeviceDatabase ourInstance = new DeviceDatabase();
    public static DeviceDatabase getInstance() {
        return ourInstance;
    }
    private DeviceDatabase() {
    }

    private static String _devicesDatabase = "devices";
    private static String _deviceId = "deviceid";
    private static String _deviceType = "deviceType";
    private static String _deviceName = "deviceName";

    public List<Device> getDevices(String query) {
        List<Device> devices = new ArrayList<Device>();

        List<Map<String, Object>> records = DatabaseManager.getInstance().queryDatabase(query);
        for(Map<String, Object> record : records) {
            Device device = new Device();

            device.setDeviceId((Integer) record.get(_deviceId));
            device.setDeviceName((String)record.get(_deviceName));
            device.setDeviceType((String)record.get(_deviceType));

            devices.add(device);
        }

        return devices;
    }
}
