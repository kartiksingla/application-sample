package com.sample.jmx.model;

public class Device implements DeviceMBean {

    private String deviceName;

    public String getDeviceName() {
        System.out.println("Device name returned " + deviceName);
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        System.out.println("Device name set : " + deviceName);
    }

    public void turnOnDevice(String model) {
        System.out.println("Device Turned on with model : " + model);
    }

}
