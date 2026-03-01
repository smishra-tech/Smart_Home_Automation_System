# Smart Home Automation Simulator

A modular, extensible **JavaFX-based Smart Home Automation Simulator** built using modern Object-Oriented Programming principles and classic design patterns.

This project simulates controlling and managing smart devices (Lights, Thermostats, Security Cameras) across multiple rooms within a virtual home environment.

It demonstrates clean architecture, SOLID principles, and scalable system design suitable for real-world IoT-inspired applications.

---

## Features

- Room-based device management (Kitchen, Living Room, etc.)
- Turn devices ON / OFF
- Adjust brightness (Light)
- Control temperature (Thermostat)
- Simulate camera functionality (Security Camera)
- Real-time event notification panel
- Extensible device architecture
- Modular Java + Maven configuration

---

## Architecture Overview

### 1️ Main (JavaFX Application)
- Launches the UI
- Manages high-level application logic
- Maintains a list of `Room` objects

### 2️ Room
- Represents a physical location
- Aggregates multiple `Device` instances

### 3️ Device (Abstract Base Class)
Defines shared attributes and behaviors:
- `name`
- `location`
- `state`
- `turnOn()`
- `turnOff()`
- `getStatus()`

### Concrete Device Implementations

- **Light** → Supports brightness control  
- **Thermostat** → Supports temperature adjustment  
- **SecurityCamera** → Supports snapshot simulation  

---

## Design Patterns Used

### Factory Pattern
Used for scalable creation of device objects.  
New device types can be added without modifying existing logic.

### Observer Pattern
Devices notify the UI when their state changes.  
Enables real-time updates in the notification panel.

### State Pattern
Each device maintains internal `OnState` and `OffState`.  
Switching states changes behavior without altering core logic.

---

## SOLID Principles Applied

- **Single Responsibility** – Each class has one clear purpose.
- **Open/Closed Principle** – Add new device types without modifying existing code.
- **Liskov Substitution** – Any `Device` reference can hold subclass instances.
- **Interface Segregation** – Devices expose only relevant behaviors.
- **Dependency Inversion** – UI depends on abstractions, not concrete implementations.

---

## Project Structure

```
Smart_Home_Automation_System
│
├── src/main/java
│   ├── application      → JavaFX UI layer
│   ├── devices          → Device hierarchy
│   ├── model            → Room and domain logic
│   └── module-info.java
│
├── pom.xml
└── target
```

---

## Tech Stack

- Java 21 (LTS)
- JavaFX 21
- Maven
- Modular Java (module-info.java)

---

## How to Run

### Using IntelliJ
1. Open the project as a Maven project
2. Reload Maven
3. Use Maven panel → `javafx:run`

### Using Terminal (if Maven installed globally)

```bash
mvn clean javafx:run
```

- Real-world simulation system thinking

Ideal for:
- Java Developer roles
- Backend Engineering roles
- IoT-related projects
- System Design interviews
