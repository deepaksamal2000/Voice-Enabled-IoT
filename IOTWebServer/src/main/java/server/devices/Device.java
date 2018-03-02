package server.devices;

import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("deviceName")
    private String          m_deviceName;
    @SerializedName("deviceType")
    private DeviceType      m_deviceType;

    public Device(String deviceName, DeviceType deviceType) {
        this.m_deviceName = deviceName;
        this.m_deviceType = deviceType;
    }

    public String getDeviceName() {
        return this.m_deviceName;
    }

    public void setDeviceName(String name) {
        this.m_deviceName = name;
    }

    public DeviceType getDeviceType() {
        return  this.m_deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.m_deviceType = deviceType;
    }
}
