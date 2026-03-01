package devices;

public class Fan  extends Device {
    private int speed = 50;  //device specific property

    public Fan(String name, String location) {
        super(name, location);  //calling the parent constructor
    }

    public void turnOn() {
        this.state = new OnState(); //status on
        addEvent("Fan turned ON"); //lastEvent in parent
    }

    public void turnOff() {
        this.state = new OffState();
        addEvent("Fan turned OFF");
    }

    public int getSpeed() {
        return speed;
    }

    public void setBrightness(int value) {
        this.speed = value;
        addEvent("Speed set to " + value + "%");
    }

    @Override
    public String getStatus() { // on, Brightness 50%
        return state.status() + ", Speed: " + speed + "%";
    }
}