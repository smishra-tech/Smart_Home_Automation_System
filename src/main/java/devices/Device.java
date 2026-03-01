package devices;

//Device.java
public abstract class Device {
    protected String name;
    protected String location;
    protected DeviceState state; // on off
    public String lastEvent = "";

    public Device(String name, String location) {  //contructor
        this.name = name;
        this.location = location;
        this.state = new OffState(); //intial
    }

    public void addEvent(String event) {
        this.lastEvent = event;
    }

    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }

    public DeviceState getState() {
        return state;
    }

    public abstract String getStatus(); //tell the status of current state
}