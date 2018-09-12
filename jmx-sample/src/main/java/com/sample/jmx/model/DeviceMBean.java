package com.sample.jmx.model;

public interface DeviceMBean {

    public String getDeviceName();
    
    public void setDeviceName(String deviceName);
    
    public void turnOnDevice(String model);
}
