package devices;

//Light.java
public class Light extends Device {
    private int brightness = 50;  //device specific property

    public Light(String name, String location) {
        super(name, location);  //calling the parent constructor
    }

    public void turnOn() {
        this.state = new OnState(); //status on
        addEvent("Light turned ON"); //lastEvent in parent
    }

    public void turnOff() {
        this.state = new OffState();
        addEvent("Light turned OFF");
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int value) {
        this.brightness = value;
        addEvent("Brightness set to " + value + "%");
    }

    @Override
    public String getStatus() { // on, Brightness 50%
        return state.status() + ", Brightness: " + brightness + "%";
    }
}