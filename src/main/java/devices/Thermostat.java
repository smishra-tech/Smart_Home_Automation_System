package devices;

//Thermostat.java
public class Thermostat extends Device {
    private int temperature = 22;

    public Thermostat(String name, String location) {
        super(name, location);
    }

    public void turnOn() {
        this.state = new OnState();
        addEvent("Thermostat turned ON");
    }

    public void turnOff() {
        this.state = new OffState();
        addEvent("Thermostat turned OFF");
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        addEvent("Temperature set to " + temp + "°C"); //lastEvent in Parent
    }

    @Override
    public String getStatus() {
        return state.status() + ", " + temperature + "°C";
    }
}