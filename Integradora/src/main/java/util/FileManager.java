package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import structures.DoubleLinkedList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final Path dataFolder;
    private final Path dataRoutes;
    private final Path dataIncidents;
    private final Path dataPassengers;
    private final Path dataDrivers;
    private final Gson gson;

    public FileManager() {
        Path dataProject = Paths.get(System.getProperty("user.dir"));
        dataFolder = dataProject.resolve("src/main/data");
        dataRoutes = dataFolder.resolve("routes.json");
        dataIncidents = dataFolder.resolve("incidents.json");
        dataPassengers = dataFolder.resolve("passengers.json");
        dataDrivers = dataFolder.resolve("drivers.json");
        gson = new Gson();
    }

    public void initialize() throws IOException {
        if (!Files.exists(dataFolder)) {
            Files.createDirectories(dataFolder);
        }
    }

    public void createFile(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Failed to create file");
        }
    }

    private <T> String saveToJson(Path filePath, T data) {
        try {
            initialize();
            createFile(filePath);
            String jsonFormat = gson.toJson(data);
            Files.writeString(filePath, jsonFormat);
            return "The file was created successfully";
        } catch (IOException e) {
            return "The file couldn't be created";
        }
    }

    private <T> T loadFromJson(Path filePath, Type type) {
        try {
            String json = Files.readString(filePath);
            return gson.fromJson(json, type);
        } catch (IOException e) {
            return null;
        }
    }


    private <T extends Comparable<T> & Identifiable> List<T> toList(DoubleLinkedList<T> list) {
        List<T> result = new ArrayList<>();

        if(list.isEmpty()) return result;

        int n = list.getSize();

        for (int i=0; i<n; i++) {
            result.add(list.get(i));
        }

        return result;
    }


    private <T extends Comparable<T> & Identifiable> DoubleLinkedList<T> toDoublyLinkedList(List<T> list) {
        DoubleLinkedList<T> linkedList = new DoubleLinkedList<>();
        if (list != null) {
            for (T item : list) {
                linkedList.add(item);
            }
        }
        return linkedList;
    }


    public String saveRoutes(DoubleLinkedList<Route> routes) {
        return saveToJson(dataRoutes, toList(routes));
    }

    public String saveIncidents(DoubleLinkedList<SecurityIncident> incidents) {
        //Path path = dataFolder.resolve(fileName);
        return saveToJson(dataIncidents,toList(incidents));
    }

    public String savePassengers(DoubleLinkedList<Passenger> passengers) {
        //Path path = dataFolder.resolve(fileName);
        return saveToJson(dataPassengers, toList(passengers));
    }

    public String saveDrivers(DoubleLinkedList<Driver> drivers) {
        //Path path = dataFolder.resolve(fileName);
        return saveToJson(dataDrivers, toList(drivers));
    }

    public DoubleLinkedList<Route> loadRoutes() {
        //Path filePath = dataFolder.resolve(fileName);
        Type type = new TypeToken<List<Route>>() {}.getType();
        List<Route> tempList = loadFromJson(dataRoutes, type);
        return toDoublyLinkedList(tempList);
    }

    public DoubleLinkedList<SecurityIncident> loadSecurityIncidents() {
        //Path filePath = dataFolder.resolve(fileName);
        Type type = new TypeToken<List<SecurityIncident>>() {}.getType();
        List<SecurityIncident> tempList = loadFromJson(dataIncidents, type);
        return toDoublyLinkedList(tempList);
    }

    public DoubleLinkedList<Passenger> oadPassengers() {
        //Path filePath = dataFolder.resolve(fileName);
        Type type = new TypeToken<List<Passenger>>() {}.getType();
        List<Passenger> tempList = loadFromJson(dataPassengers, type);
        return toDoublyLinkedList(tempList);
    }

    public DoubleLinkedList<Driver> loadDrivers() {
        //Path filePath = dataFolder.resolve(fileName);
        Type type = new TypeToken<List<Driver>>() {}.getType();
        List<Driver> tempList = loadFromJson(dataDrivers, type);
        return toDoublyLinkedList(tempList);
    }
}

