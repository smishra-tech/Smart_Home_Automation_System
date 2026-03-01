package devices;

//SecurityCamera.java
public class SecurityCamera extends Device {
    private String snapshot = "No activity.";

    public SecurityCamera(String name, String location) {
        super(name, location);
    }

    public void turnOn() {
        this.state = new OnState();
        addEvent("Camera turned ON");
    }

    public void turnOff() {
        this.state = new OffState();
        addEvent("Camera turned OFF");
    }

    public void takeSnapshot() {
        this.snapshot = "Snapshot: [" + name + " in " + location + "]";
        addEvent("Snapshot taken");
    }

    public String getSnapshot() { return snapshot; }

    @Override
    public String getStatus() { return state.status(); }
}