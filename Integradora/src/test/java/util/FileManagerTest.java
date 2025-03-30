package util;

import model.*;
import structures.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private FileManager fileManager;

    void setUpDataFolderNotCreated() {
        fileManager = new FileManager("test", "dataTest1");

        try {
            Files.deleteIfExists(fileManager.getDataFolder());
        } catch (IOException e) {}
    }

    void setUpDataFolderCreated() {
        fileManager = new FileManager("test", "dataTest2");

        try {
            Files.createDirectories(fileManager.getDataFolder());
        } catch (IOException e) {}
    }

    void setUpFileInDataNotCreated() {
        fileManager = new FileManager("test", "dataTest3");
        fileManager.initialize();

        try {
            Files.deleteIfExists(fileManager.getDataFolder().resolve("fileTest3.json"));
        } catch (IOException e) {}
    }

    void setUpFileInDataCreated() {
        fileManager = new FileManager("test", "dataTest4");
        fileManager.initialize();

        try {
            Files.createFile(fileManager.getDataFolder().resolve("fileTest4.json"));
        } catch (IOException e) {}
    }

    void setUpDoubleLinkedListWithRoutes() {
        fileManager = new FileManager("test", "dataTestFileRoutes");

        try {
            Files.deleteIfExists(fileManager.getDataRoutes());
        } catch (IOException e) {}
    }

    void setUpDoubleLinkedListWithOutRoutes() {
        fileManager = new FileManager("test", "dataTestFileRoutes1");

        try {
            Files.deleteIfExists(fileManager.getDataRoutes());
        } catch (IOException e) {}
    }

    void setUpIncidents() {
        fileManager = new FileManager("test" , "dataTestFileIncidents");

        try {
            Files.deleteIfExists(fileManager.getDataIncidents());
        } catch (IOException e) {}
    }

    void setUpPassengers() {
        fileManager = new FileManager("test", "dataTestFilePassengers");

        try {
            Files.deleteIfExists(fileManager.getDataPassengers());
        } catch (IOException e) {}
    }

    @Test
    void test1Inicialize() {
        setUpDataFolderNotCreated();

        assertTrue(fileManager.initialize());
        assertDoesNotThrow(() -> fileManager.initialize());
        assertTrue(Files.exists(fileManager.getDataFolder()));
    }

    @Test
    void test2Inicialize() {
        setUpDataFolderCreated();

        assertFalse(fileManager.initialize());
        assertTrue(Files.exists(fileManager.getDataFolder()));
    }


    @Test
    void test3CreateFile() {
        setUpFileInDataNotCreated();

        Path path = fileManager.getDataFolder().resolve("fileTest3.json");

        assertTrue(fileManager.createFile(path));
        assertDoesNotThrow(() -> fileManager.createFile(path));
    }

    @Test
    void test4CreateFile() {
        setUpFileInDataCreated();

        Path path = fileManager.getDataFolder().resolve("fileTest4.json");

        assertFalse(fileManager.createFile(path));
        assertTrue(Files.exists(path));
    }


    @Test
    void test5SaveRoutes() {

        setUpDoubleLinkedListWithRoutes();

        DoubleLinkedList<Route> routes = new DoubleLinkedList<>();

        routes.add(new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4"));
        routes.add(new Route("2",0.6, 7, "Calle 3 # 4-5", "Calle 4 # 5-6"));

        assertEquals("The file was created successfully", fileManager.saveRoutes(routes));

        String fileCont = "";
        try {
            fileCont = Files.readString(fileManager.getDataRoutes());
        } catch (IOException e) {}

        assertTrue(!fileCont.isEmpty());
    }

    @Test
    void test6SaveRoutes() {
        setUpDoubleLinkedListWithOutRoutes();

        DoubleLinkedList<Route> routes = new DoubleLinkedList<>();

        assertEquals("The file was created successfully", fileManager.saveRoutes(routes));

        String fileCont = "";
        try {
            fileCont = Files.readString(fileManager.getDataRoutes());
        } catch (IOException e) {}

        assertTrue(fileCont.equals("[]"));
    }

    @Test
    void test7SaveIncidents() {
        setUpIncidents();

        DoubleLinkedList<SecurityIncident> incidents = new DoubleLinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date1 =  null;
        Date date2 = null;
        try {
            date1 = dateFormat.parse("2025-3-1 1:01");
            date2 = dateFormat.parse("2025-2-1 5:01");
        } catch (ParseException e) {}

        incidents.add(new SecurityIncident("1", TypeOfIncident.ACCIDENT, "Calle 1 # 2-3", date1, "Car crash", IncidentStatus.PENDING));
        incidents.add(new SecurityIncident("2", TypeOfIncident.FIRE, "Calle 2 # 3-4", date2, "Building on fire", IncidentStatus.IN_PROCESS));

        assertEquals("The file was created successfully", fileManager.saveIncidents(incidents));

        String fileCont = "";
        try {
            fileCont = Files.readString(fileManager.getDataIncidents());
        } catch (IOException e) {}

        assertTrue(!fileCont.isEmpty());

    }

    @Test
    void test8SaveIncidents() {
        setUpIncidents();

        DoubleLinkedList<SecurityIncident> incidents = new DoubleLinkedList<>();

        assertEquals("The file was created successfully", fileManager.saveIncidents(incidents));

        String fileCont = "";
        try {
            fileCont = Files.readString(fileManager.getDataIncidents());
        } catch (IOException e) {}

        assertTrue(fileCont.equals("[]"));
    }

    @Test
    void test9SavePassengers() {
        setUpPassengers();


    }

    @Test
    void saveDrivers() {
    }

    @Test
    void loadRoutes() {
    }

    @Test
    void loadSecurityIncidents() {
    }

    @Test
    void loadPassengers() {
    }

    @Test
    void loadDrivers() {
    }
}