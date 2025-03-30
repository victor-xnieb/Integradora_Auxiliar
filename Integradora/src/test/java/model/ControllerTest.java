package model;

import exceptions.DriverAlreadyExistsException;
import exceptions.IncidentAlreadyExistsException;
import exceptions.PassengerAlreadyExistsException;
import exceptions.RouteAlreadyExistsException;
import org.junit.jupiter.api.Test;
import structures.DoubleLinkedList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller cont;

    void setupEmpty() {
        cont = new Controller();
    }

    void setupRoutes() {
        cont = new Controller();

        cont.addRoute(new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4"));
        cont.addRoute(new Route("2",0.6, 7, "Calle 3 # 4-5", "Calle 4 # 5-6"));
    }

    void setupSecurityIncidents() {
        cont = new Controller();

        cont.addSecurityIncident(new SecurityIncident("1", TypeOfIncident.ACCIDENT, "Calle 1 # 2-3", cont.convertToDate("2025-3-1 1:01"), "Car crash", IncidentStatus.PENDING));
        cont.addSecurityIncident(new SecurityIncident("2", TypeOfIncident.FIRE, "Calle 2 # 3-4", cont.convertToDate("2025-2-1 5:01"), "Building on fire", IncidentStatus.IN_PROCESS));
    }

    void setupPassengers() {
        cont = new Controller();

        cont.addRoute(new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4"));
        cont.addPassenger(new Passenger("1011325932", "Andres", cont.getRoutes().getFirst().getData(),"3102424021"));
        cont.addPassenger(new Passenger("1234567890", "Felipe", cont.getRoutes().getFirst().getData(),"3112313213"));
    }

    void setupDrivers() {
        cont = new Controller();

        cont.addDriver(new Driver("1", "Jacobo", new Vehicle("ABC123", "Renault"), DriverStatus.ON_ROUTE));
        cont.addDriver(new Driver("2", "Rodriguez", new Vehicle("DEF456", "Chevrolet"), DriverStatus.AVAILABLE));
    }

    @Test
    void test1GetRoutes() {
        setupEmpty();

        assertNull(cont.getRoutes().getFirst());
        assertNull(cont.getRoutes().getLast());
        assertEquals(0, cont.getRoutes().getSize());
    }

    @Test
    void test2GetRoutes() {
        setupRoutes();

        assertNotNull(cont.getRoutes().getFirst());
        assertNotNull(cont.getRoutes().getLast());
        assertEquals(2, cont.getRoutes().getSize());
        assertEquals("1", cont.getRoutes().getFirst().getData().getId());
        assertEquals("2", cont.getRoutes().getFirst().getNext().getData().getId());
    }

    @Test
    void testGetSecurityIncidents1() {
        setupEmpty();

        assertNull(cont.getSecurityIncidents().getFirst());
        assertNull(cont.getSecurityIncidents().getLast());
        assertEquals(0, cont.getSecurityIncidents().getSize());
    }


    @Test
    void testGetSecurityIncidents2() throws IncidentAlreadyExistsException{
        setupSecurityIncidents();

        assertNotNull(cont.getSecurityIncidents().getFirst());
        assertNotNull(cont.getSecurityIncidents().getLast());
        assertEquals(2, cont.getSecurityIncidents().getSize());
        assertEquals("1", cont.getSecurityIncidents().getFirst().getData().getId());
        assertEquals("2", cont.getSecurityIncidents().getFirst().getNext().getData().getId());
    }

    @Test
    void testGetPassengers1() {
        setupEmpty();

        assertNull(cont.getPassengers().getFirst());
        assertNull(cont.getPassengers().getLast());
        assertEquals(0, cont.getPassengers().getSize());
    }

    @Test
    void testGetPassengers2() {
        setupPassengers();

        assertNotNull(cont.getPassengers().getFirst());
        assertEquals(2, cont.getPassengers().getSize());
        assertEquals("1011325932", cont.getPassengers().getFirst().getData().getId());
        assertEquals("1234567890", cont.getPassengers().getFirst().getNext().getData().getId());
    }

    @Test
    void test1GetDrivers() {
        setupEmpty();

        assertNull(cont.getDrivers().getFirst());
        assertNull(cont.getDrivers().getLast());
        assertEquals(0, cont.getDrivers().getSize());
    }

    @Test
    void test2GetDrivers() {
        setupDrivers();

        assertNotNull(cont.getDrivers().getFirst());
        assertEquals(2, cont.getDrivers().getSize());
        assertEquals("1", cont.getDrivers().getFirst().getData().getId());
        assertEquals("2", cont.getDrivers().getFirst().getNext().getData().getId());
    }

    @Test
    void saveRoutesToJson() {

    }

    @Test
    void loadRoutesFromJson() {
    }

    @Test
    void testCreateRoute1() {
        setupEmpty();

        Route route = null;

        route = cont.createRoute(5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");

        assertNotNull(route);
        assertEquals(5, route.getDistance());
        assertEquals(10, route.getTime());
        assertEquals("Calle 1 # 2-3", route.getStartPlace());
        assertEquals("Calle 2 # 3-4", route.getEndPlace());
    }

    @Test
    void testCreateRoute2() {
        setupRoutes();

        Route route = null;

        route = cont.createRoute(5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");

        assertNotNull(route);
        assertEquals(5, route.getDistance());
        assertEquals(10, route.getTime());
        assertEquals("Calle 1 # 2-3", route.getStartPlace());
        assertEquals("Calle 2 # 3-4", route.getEndPlace());
    }

    @Test
    void testAddRoute1() {
        setupEmpty();

        assertTrue(cont.addRoute(new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4")));

        Route route = cont.getRoutes().getFirst().getData();

        assertNotNull(route);
        assertEquals("1", route.getId());
        assertEquals(0.5, route.getDistance());
        assertEquals(10, route.getTime());
        assertEquals("Calle 1 # 2-3", route.getStartPlace());
        assertEquals("Calle 2 # 3-4", route.getEndPlace());
    }

    @Test
    void testAddRoute2() {
        setupRoutes();
        Route route = new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");

        assertThrows(RouteAlreadyExistsException.class, () -> {
            cont.addRoute(route);
        });
    }

    @Test
    void saveSecurityIncidentsToJson() {

    }

    @Test
    void loadIncidentsFromJson() {
    }

    @Test
    void testCreateSecurityIncident1() {
        setupEmpty();

        SecurityIncident incident = null;

        incident = cont.createSecurityIncident(TypeOfIncident.ACCIDENT, "Calle 1 # 2-3", cont.convertToDate("2025-3-1 1:01"), "Car crash", IncidentStatus.PENDING);

        assertNotNull(incident);
        assertEquals(TypeOfIncident.ACCIDENT, incident.getType());
        assertEquals("Calle 1 # 2-3", incident.getLocation());
        assertEquals("2025-3-1 1:01", incident.getReportDateString().toString());
        assertEquals("Car crash", incident.getDescription());
        assertEquals(IncidentStatus.PENDING, incident.getStatus());
    }

    @Test
    void testCreateSecurityIncident2() {
        setupSecurityIncidents();

        SecurityIncident incident = null;

        incident = cont.createSecurityIncident(TypeOfIncident.FIRE, "Calle 1 # 2-3", cont.convertToDate("2025-3-1 1:01"), "Car crash", IncidentStatus.IN_PROCESS);

        assertNotNull(incident);
        assertEquals(TypeOfIncident.FIRE, incident.getType());
        assertEquals("Calle 1 # 2-3", incident.getLocation());
        assertEquals("2025-3-1 1:01", incident.getReportDateString().toString());
        assertEquals("Car crash", incident.getDescription());
        assertEquals(IncidentStatus.IN_PROCESS, incident.getStatus());
    }

    @Test
    void testAddSecurityIncident1() {
        setupEmpty();

        assertTrue(cont.addSecurityIncident(new SecurityIncident("1", TypeOfIncident.ACCIDENT, "Calle 1 # 2-3", cont.convertToDate("2025-3-1 1:01"), "Car crash", IncidentStatus.PENDING)));

        SecurityIncident incident = cont.getSecurityIncidents().getFirst().getData();

        assertNotNull(incident);
        assertEquals("1", incident.getId());
        assertEquals(TypeOfIncident.ACCIDENT, incident.getType());
        assertEquals("Calle 1 # 2-3", incident.getLocation());
        assertEquals("2025-3-1 1:01", incident.getReportDateString());
        assertEquals("Car crash", incident.getDescription());
        assertEquals(IncidentStatus.PENDING, incident.getStatus());
    }

    @Test
    void testAddSecurityIncident2() {
        setupSecurityIncidents();
        SecurityIncident incident = new SecurityIncident("1", TypeOfIncident.ACCIDENT, "Calle 1 # 2-3", cont.convertToDate("2025-3-1 1:01"), "Car crash", IncidentStatus.PENDING);

        assertThrows(IncidentAlreadyExistsException.class, () -> {
            cont.addSecurityIncident(incident);
        });
    }

    @Test
    void savePassengersToJson() {
    }

    @Test
    void loadPassengersFromJson() {
    }

    @Test
    void testCreatePassenger1() {
        setupEmpty();

        Passenger passenger = null;
        Route route = new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");


        passenger = cont.createPassenger("1", "Victor", route, "3121312331");

        assertNotNull(passenger);
        assertEquals("1", passenger.getId());
        assertEquals("Victor", passenger.getName());
        assertEquals(route, passenger.getAssignedRoute());
        assertEquals("3121312331", passenger.getContact());
    }

    @Test
    void testCreatePassenger2() {
        setupPassengers();

        Passenger passenger = null;
        Route route = new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");


        passenger = cont.createPassenger("1", "Victor", route, "3121312331");

        assertNotNull(passenger);
        assertEquals("1", passenger.getId());
        assertEquals("Victor", passenger.getName());
        assertEquals(route, passenger.getAssignedRoute());
        assertEquals("3121312331", passenger.getContact());
    }

    @Test
    void testAddPassenger1() {
        setupEmpty();

        Route route = new Route("1", 0.5, 10, "Calle 1 # 2-3", "Calle 2 # 3-4");

        assertTrue(cont.addPassenger(new Passenger("1011325932", "Andres", route,"3102424021")));

        Passenger passenger = cont.getPassengers().getFirst().getData();

        assertNotNull(passenger);
        assertEquals("1011325932", passenger.getId());
        assertEquals("Andres",passenger.getName());
        assertEquals(route, passenger.getAssignedRoute());
        assertEquals("3102424021", passenger.getContact());
    }

    @Test
    void testAddPassenger2() {
        setupPassengers();
        Route route = cont.getRoutes().getFirst().getData();
        Passenger passenger = new Passenger("1011325932", "Andres", route,"3102424021");

        assertThrows(PassengerAlreadyExistsException.class, () -> {
            cont.addPassenger(passenger);
        });
    }

    @Test
    void saveDriversToJson() {
    }

    @Test
    void loadDriversFromJson() {
    }

    @Test
    void testCreateDriver1() {
        setupEmpty();

        Driver driver = null;
        Vehicle vehicle = new Vehicle("ABC123", "Renault");
        driver = cont.createDriver("1", "Jacobo", vehicle, DriverStatus.ON_ROUTE);

        assertNotNull(driver);
        assertEquals("1", driver.getId());
        assertEquals("Jacobo", driver.getName());
        assertEquals(vehicle, driver.getVehicle());
        assertEquals(DriverStatus.ON_ROUTE, driver.getStatus());
    }

    @Test
    void testCreateDriver2() {
        setupDrivers();

        Driver driver = null;
        Vehicle vehicle = new Vehicle("ABC123", "Renault");
        driver = cont.createDriver("1", "Jacobo", vehicle, DriverStatus.ON_ROUTE);

        assertNotNull(driver);
        assertEquals("1", driver.getId());
        assertEquals("Jacobo", driver.getName());
        assertEquals(vehicle, driver.getVehicle());
        assertEquals(DriverStatus.ON_ROUTE, driver.getStatus());
    }

    @Test
    void testCreateVehicle1() {
        setupEmpty();

        Vehicle vehicle = null;
        vehicle = cont.createVehicle("ABC123", "Renault");

        assertNotNull(vehicle);
        assertEquals("ABC123", vehicle.getLicensePlate());
        assertEquals("Renault", vehicle.getBrand());
    }

    @Test
    void testCreateVehicle2() {
        setupDrivers();

        Vehicle vehicle = null;
        vehicle = cont.createVehicle("ABC123", "Renault");

        assertNotNull(vehicle);
        assertEquals("ABC123", vehicle.getLicensePlate());
        assertEquals("Renault", vehicle.getBrand());
    }

    @Test
    void testAddDriver1() {
        setupEmpty();

        Vehicle vehicle = new Vehicle("ABC123", "Renault");


        assertTrue(cont.addDriver(new Driver("1", "Jacobo", vehicle, DriverStatus.ON_ROUTE)));
        Driver driver = cont.getDrivers().getFirst().getData();

        assertNotNull(driver);
        assertEquals("1", driver.getId());
        assertEquals("Jacobo",driver.getName());
        assertEquals(vehicle, driver.getVehicle());
        assertEquals(DriverStatus.ON_ROUTE, driver.getStatus());
    }

    @Test
    void testAddDriver2() {
        setupDrivers();
        Driver driver = cont.getDrivers().getFirst().getData();

        assertThrows(DriverAlreadyExistsException.class, () -> {
            cont.addDriver(driver);
        });
    }

    @Test
    void addObjectsLoaded() {
    }

    @Test
    void test1GetRoutesString() {
        setupEmpty();

        assertEquals("", cont.getRoutesString());
    }

    @Test
    void test2GetRoutesString() {
        setupRoutes();
        Route route1 = cont.getRoutes().getFirst().getData();
        Route route2 = cont.getRoutes().getFirst().getNext().getData();

        String expected = "1. ID: 1, Distance: 0.5 km , Travel time: 10 minutes, Start place: Calle 1 # 2-3, End place: Calle 2 # 3-4\n" +
                "2. ID: 2, Distance: 0.6 km , Travel time: 7 minutes, Start place: Calle 3 # 4-5, End place: Calle 4 # 5-6\n";

        assertEquals(expected, cont.getRoutesString());
    }

    @Test
    void test1SearchSecurityIncident() {
        setupEmpty();

        String expected = "\n\nThere Are not incidents saved in the system. please, add or load some incidents to do the search.\n\n";

        assertEquals(expected, cont.searchSecurityIncident("1"));
    }

    @Test
    void test2SearchSecurityIncident() {
        setupSecurityIncidents();

        String expected = "ID: 1, Type of Incident: ACCIDENT, Location: Calle 1 # 2-3, Report date: 2025-03-01, Report time: 01:01, Description: Car crash, Incident status: PENDING";

        assertEquals(expected, cont.searchSecurityIncident("1"));
    }

    @Test
    void test3SearchSecurityIncident() {
        setupSecurityIncidents();

        String expected = "No incident found with that ID";

        assertEquals(expected, cont.searchSecurityIncident("5"));
    }

    @Test
    void test1SearchDriver() {
        setupEmpty();

        String expected = "\n\nThere Are not drivers saved in the system. please, add or load some drivers to do the search.\n\n";

        assertEquals(expected, cont.searchDriver("Jacobo"));
    }

    @Test
    void test2SearchDriver() {
        setupDrivers();
        String expected = "Name: Jacobo\n" +
                "ID: 1\n" +
                "Status: ON_ROUTE\n" +
                "Vehicle: License Plate: ABC123, Brand: Renault";

        assertEquals(expected, cont.searchDriver("Jacobo"));
    }

    @Test
    void test3SearchDriver() {
        setupDrivers();
        String expected = "No driver found with that name";

        assertEquals(expected, cont.searchDriver("Pepe"));
    }

    @Test
    void test1SortIncidentsByID() {
        setupEmpty();

        assertEquals("", cont.sortIncidentsByID());
        assertNull(cont.getSecurityIncidents().getFirst().getData());
    }

    @Test
    void test2SortIncidentsByID2() {
        setupSecurityIncidents();

        cont.sortIncidentsByID();

        assertEquals("1",cont.getSecurityIncidents().getFirst().getData().getId());
        assertEquals("2",cont.getSecurityIncidents().getFirst().getNext().getData().getId());
    }  
    @Test
    void test3sortIncidentsByID() {
        setupSecurityIncidents();
        
        SecurityIncident securityIncident = new SecurityIncident("0", TypeOfIncident.THEFT , "Calle 7 # 8-9", cont.convertToDate("2025-3-22 1:01"), "The central bank was robbed", IncidentStatus.PENDING);
        cont.addSecurityIncident(securityIncident);

        assertEquals("0", cont.getSecurityIncidents().getLast().getData().getId());

        cont.sortIncidentsByID();

        assertEquals("0",cont.getSecurityIncidents().getFirst().getData().getId());
        assertEquals("1",cont.getSecurityIncidents().getFirst().getNext().getData().getId());
        assertEquals("2",cont.getSecurityIncidents().getLast().getData().getId());
    }
    

    @Test
    void test1searchBestRoute() {
       setupEmpty();

       String expected = "There are no routes registered yet!";
       assertEquals(expected, cont.searchBestRoute());
    }
    // QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
    @Test
    void test2searchBestRoute() {
        setupRoutes();

      String bestRoute= cont.searchBestRoute();
      String expected = "ID: 2, Distance: 0.6 km , Travel time: 7 minutes, Start place: Calle 3 # 4-5, End place: Calle 4 # 5-6";
      assertEquals(expected, bestRoute);
    }

    @Test
    void test1SortDriversByName() {
        setupEmpty();
        String list = cont.sortDriversByName();
        assertEquals("", list);

    }
    @Test
    void test2SortDriversByName() {
        setupDrivers();
        cont.sortDriversByName();
        assertEquals("1", cont.getDrivers().getFirst().getData().getId());

    }

    @Test
    void test1sortRouteByTravelTime() {
        setupEmpty();
        String list = cont.sortRouteByTravelTime();
        assertEquals("", list);

    }
    @Test
    void test2sortRouteByTravelTime() {
        setupRoutes();
        assertEquals("1", cont.getRoutes().getFirst().getData().getId());
        assertEquals("2", cont.getRoutes().getLast().getData().getId());
        cont.sortRouteByTravelTime();
        assertEquals("2", cont.getRoutes().getFirst().getData().getId());
        assertEquals("1", cont.getRoutes().getLast().getData().getId());


    }

    @Test
    void sortIncidentsByDateAndTime() {
    }


    @Test
    void searchRouteByID() {

    }
}