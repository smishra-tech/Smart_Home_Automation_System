package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Room;

import java.util.*;

import devices.Device;
import devices.Fan;
import devices.Light;
import devices.SecurityCamera;
import devices.Thermostat;

public class Main extends Application {
    private List<Room> rooms = new ArrayList<>();
    private ListView<String> roomListView = new ListView<>();
    private VBox deviceBox = new VBox(10);
    private TextArea notificationArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        // ----- Data setup -----
        Room kitchen = new Room("Kitchen");
        kitchen.addDevice(new Light("Kitchen Light", "Kitchen"));
        kitchen.addDevice(new Thermostat("Kitchen Thermostat", "Kitchen"));
        kitchen.addDevice(new Fan("Kitchen Fan", "Kitchen"));

        Room living = new Room("Living Room");
        living.addDevice(new Light("Main Light", "Living Room"));
        living.addDevice(new SecurityCamera("Cam 1", "Living Room"));

        Room lobby = new Room("Lobby");
        lobby.addDevice(new Light("Lobby Light", "Lobby"));
        lobby.addDevice(new Thermostat("Lobby Thermostat", "Lobby"));
        lobby.addDevice(new Fan("Lobby Fan", "Lobby"));

        rooms.add(kitchen); rooms.add(living);
        rooms.add(lobby);

        roomListView.getItems().addAll("Kitchen", "Living Room", "Lobby");
        roomListView.getSelectionModel().select(0);

        // ----- Layout -----
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 350);

        VBox leftPane = new VBox(new Label("Rooms"), roomListView);
        leftPane.setPadding(new Insets(10));
        leftPane.setSpacing(10);

        deviceBox.setPadding(new Insets(10));
        deviceBox.setSpacing(15);

        VBox rightPane = new VBox(deviceBox, new Label("Recent Events:"), notificationArea);
        rightPane.setSpacing(15);
        rightPane.setPadding(new Insets(10));
        VBox.setVgrow(notificationArea, Priority.ALWAYS);

        root.setLeft(leftPane);
        root.setCenter(rightPane);

        // ----- Event logic -----
        roomListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            showDevices(newVal);
        });

        // ----- Start with first room -----
        showDevices(roomListView.getSelectionModel().getSelectedItem());

        notificationArea.setEditable(false);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Smart Home Automation Simulator");
        primaryStage.show();
    }

    private void showDevices(String roomName) {
        deviceBox.getChildren().clear();
        Room currentRoom = rooms.stream().filter(r -> r.getName().equals(roomName)).findFirst().orElse(null);
        if (currentRoom == null) return;
        for (Device d : currentRoom.getDevices()) {
            VBox controls = new VBox(5);
            controls.setPadding(new Insets(5));
            controls.setStyle("-fx-border-color: #aaa; -fx-border-width: 1; -fx-background-color: #f5f5f5;");
            Label title = new Label(d.getName() + " (" + d.getClass().getSimpleName() + ")");
            title.setStyle("-fx-font-weight: bold;");
            Label status = new Label("Status: " + d.getStatus());
            controls.getChildren().addAll(title, status);

            if (d instanceof Light) { // UI for light ..
                Light l = (Light) d;
                Button onBtn = new Button("Turn On");
                Button offBtn = new Button("Turn Off");
                Slider slider = new Slider(0, 100, l.getBrightness());
                Label brightLbl = new Label("Brightness: " + l.getBrightness() + "%");

                onBtn.setOnAction(e -> { l.turnOn(); status.setText("Status: " + l.getStatus()); addNotif(l); });

                offBtn.setOnAction(e -> { l.turnOff(); status.setText("Status: " + l.getStatus()); addNotif(l); });

                slider.valueProperty().addListener((obs, oldV, newV) -> {
                    l.setBrightness(newV.intValue());
                    brightLbl.setText("Brightness: " + newV.intValue() + "%");
                });

                slider.setOnMouseReleased(e -> { status.setText("Status: " + l.getStatus()); addNotif(l); });
                controls.getChildren().addAll(onBtn, offBtn, brightLbl, slider);
            }

            if (d instanceof Fan) { // UI for Fan ..
                Fan l = (Fan) d;
                Button onBtn = new Button("Turn On");
                Button offBtn = new Button("Turn Off");
                Slider slider = new Slider(0, 100, l.getSpeed());
                Label brightLbl = new Label("Speed: " + l.getSpeed() + "%");

                onBtn.setOnAction(e -> { l.turnOn(); status.setText("Status: " + l.getStatus()); addNotif(l); });

                offBtn.setOnAction(e -> { l.turnOff(); status.setText("Status: " + l.getStatus()); addNotif(l); });

                slider.valueProperty().addListener((obs, oldV, newV) -> {
                    l.setBrightness(newV.intValue());
                    brightLbl.setText("Speed: " + newV.intValue() + "%");
                });

                slider.setOnMouseReleased(e -> { status.setText("Status: " + l.getStatus()); addNotif(l); });
                controls.getChildren().addAll(onBtn, offBtn, brightLbl, slider);
            }

            if (d instanceof Thermostat) {
                Thermostat t = (Thermostat) d;
                Button onBtn = new Button("Turn On");
                Button offBtn = new Button("Turn Off");
                Spinner<Integer> spinner = new Spinner<>(12, 32, t.getTemperature(), 1);
                Label tempLbl = new Label("Temp: " + t.getTemperature() + "°C");
                onBtn.setOnAction(e -> { t.turnOn(); status.setText("Status: " + t.getStatus()); addNotif(t); });
                offBtn.setOnAction(e -> { t.turnOff(); status.setText("Status: " + t.getStatus()); addNotif(t); });
                spinner.valueProperty().addListener((o, ov, nv) -> {
                    t.setTemperature(nv);
                    tempLbl.setText("Temp: " + nv + "°C");
                    status.setText("Status: " + t.getStatus());
                    addNotif(t);
                });
                controls.getChildren().addAll(onBtn, offBtn, tempLbl, spinner);
            }

            if (d instanceof SecurityCamera) {
                SecurityCamera c = (SecurityCamera) d;
                Button onBtn = new Button("Turn On");
                Button offBtn = new Button("Turn Off");
                Button snapBtn = new Button("Take Snapshot");
                Label snapLbl = new Label(c.getSnapshot());
                onBtn.setOnAction(e -> { c.turnOn(); status.setText("Status: " + c.getStatus()); addNotif(c); });
                offBtn.setOnAction(e -> { c.turnOff(); status.setText("Status: " + c.getStatus()); addNotif(c); });
                snapBtn.setOnAction(e -> { c.takeSnapshot(); snapLbl.setText(c.getSnapshot()); addNotif(c); });
                controls.getChildren().addAll(onBtn, offBtn, snapBtn, snapLbl);
            }

            deviceBox.getChildren().add(controls);
        }
    }

    private void addNotif(Device device) {
        notificationArea.appendText(device.getLocation() + ": " + device.getName() + " — " + device.lastEvent + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
