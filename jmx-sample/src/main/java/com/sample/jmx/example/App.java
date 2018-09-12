package com.sample.jmx.example;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.sample.jmx.model.Device;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        ObjectName objectName = null;

        try {
            objectName = new ObjectName("com.sample.jmx.model:type=basic,name=device");
        } catch (Exception e) {
            System.out.println(e);
        }
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        Device d = new Device();

        try {
            server.registerMBean(d, objectName);
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {

        }
    }
}
