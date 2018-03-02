
import com.google.gson.GsonBuilder;
import server.devices.metadata.DeviceMetadata;
import server.devices.metadata.DeviceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Devices {
    public static void main(String[] args) {
        DeviceMetadata metadata = DeviceMetadata.getInstance();

        /*
        DeviceType type = new DeviceType();
        type.setTypeName("light");


        ArrayList<String> switchCapability = new ArrayList<String>();
        switchCapability.add("on");
        switchCapability.add("off");
        type.getCapabilities().put("switch", switchCapability);

        Map<String, Integer> intensity = new HashMap<String, Integer>();
        intensity.put("minRange", 0);
        intensity.put("maxRange", 10);
        type.getCapabilities().put("intensity", intensity);

        type.setTypeId("iot.devicetype.light");

        type.getSynonyms().add("bulb");
        type.getSynonyms().add("tubelight");



        metadata.add(type);
        */

        String metadataString = new GsonBuilder().create().toJson(metadata);
        System.out.println(metadataString);

        DeviceMetadata user= new GsonBuilder().create().fromJson(metadataString, DeviceMetadata.class);
    }
}
