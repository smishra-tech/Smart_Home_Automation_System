package model;

//Room.java
import java.util.*;

import devices.Device;

public class Room {
    private String name;

    private List<Device> devices = new ArrayList<>();

    public Room(String name) { // kitchen
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device d) { //adding light and thermo
        devices.add(d);
    }
}