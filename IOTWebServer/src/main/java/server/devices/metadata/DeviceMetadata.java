package server.devices.metadata;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import server.utils.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeviceMetadata extends ArrayList<DeviceType> {
    private static DeviceMetadata instance = load();
    public static DeviceMetadata getInstance() { return instance; }

    private DeviceMetadata() {
    }

    public String getMetadataJson() {
        String jsonResponse = "";

        try {
            jsonResponse = FileHelper.readFromInputStream(DeviceMetadata.class.getClassLoader().getResourceAsStream("devices/deviceMetadata.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }


    private static DeviceMetadata load() {
        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(DeviceMetadata.class.getClassLoader().getResource("devices/deviceMetadata.json").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DeviceMetadata metadata = gson.fromJson(reader, DeviceMetadata.class);
        return metadata;
    }
}
