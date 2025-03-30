package model;

import exceptions.*;
import structures.DoubleLinkedList;
import util.FileManager;

import java.text.ParseException;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    private final DoubleLinkedList<Route> routes;
    private final DoubleLinkedList<SecurityIncident> securityIncidents;
    private final DoubleLinkedList<Passenger> passengers;
    private final DoubleLinkedList<Driver> drivers;

    private final FileManager fileManager;

    public Controller() {

        this.routes = new DoubleLinkedList<>();
        this.securityIncidents = new DoubleLinkedList<>();
        this.passengers = new DoubleLinkedList<>();
        this.drivers = new DoubleLinkedList<>();
        this.fileManager = new FileManager();

    }


    public DoubleLinkedList<Route> getRoutes() {
        return routes;
    }


    public DoubleLinkedList<SecurityIncident> getSecurityIncidents() {
        return securityIncidents;
    }


    public DoubleLinkedList<Passenger> getPassengers() {
        return passengers;
    }


    public DoubleLinkedList<Driver> getDrivers() {
        return drivers;
    }

    //Routes management

    public String saveRoutesToJson(){
        return fileManager.saveRoutes(routes);
    }

    public DoubleLinkedList<Route> loadRoutesFromJson() {
        return fileManager.loadRoutes();
    }

    public Route createRoute(double distance, int time,String startPlace,String endPlace) {
        return new Route(distance,time,startPlace,endPlace);
    }

    public boolean addRoute(Route route) {
        if(addObject(route,routes)){
            return true;
        } else {
            throw new RouteAlreadyExistsException("The route is already in the system.");
        }
    }


    //Incidents management

    public String saveSecurityIncidentsToJson() {
        return fileManager.saveIncidents(securityIncidents);
    }

    public DoubleLinkedList<SecurityIncident> loadIncidentsFromJson() {
        return fileManager.loadSecurityIncidents();
    }

    public SecurityIncident createSecurityIncident(TypeOfIncident type, String location,Date reportDateTime, String description, IncidentStatus status) {
        return new SecurityIncident(type,location,reportDateTime,description,status);
    }

    public boolean addSecurityIncident(SecurityIncident incident) {
        if(addObject(incident,securityIncidents)) {
            return true;
        } else {
            throw new IncidentAlreadyExistsException("The security incident is already in the system.");
        }
    }

    //***************************************************++


    //Passengers management

    public String savePassengersToJson() {
        return fileManager.savePassengers(passengers);
    }


    public DoubleLinkedList<Passenger> loadPassengersFromJson() {
        return fileManager.loadPassengers();
    }

    public Passenger createPassenger(String id, String name,Route route, String contact){
        return new Passenger(id,name,route,contact);
    }

    public boolean addPassenger(Passenger passenger) {

        if(addObject(passenger,passengers)) {
            return true;
        } else {
            throw new PassengerAlreadyExistsException("The passenger is already in the system.");
        }

    }

    //************************************************************


    //Driver management

    public String saveDriversToJson() {
        return fileManager.saveDrivers(drivers);
    }


    public DoubleLinkedList<Driver> loadDriversFromJson() {
        return fileManager.loadDrivers();
    }

    public Driver createDriver(String id, String name, Vehicle vehicle, DriverStatus driverStatus) {
        return new Driver(id,name,vehicle,driverStatus);
    }

    public Vehicle createVehicle(String licensePlate, String brand) {
        return new Vehicle(licensePlate,brand);
    }

    public boolean addDriver(Driver driver){
        if(addObject(driver,drivers)) {
            return true;
        } else {
            throw new DriverAlreadyExistsException("The passenger is already in the system.");
        }
    }

    public <T extends Comparable<T> & Identifiable> boolean addObject(T object, DoubleLinkedList<T> list) {
        boolean isAdded = false;

        if(list.search(object) == null) {
            isAdded = list.add(object);
        }

        return isAdded;
    }








    public <T extends Comparable<T> & Identifiable> String addObjectsLoaded(DoubleLinkedList<T> listLoaded, DoubleLinkedList<T> list) {
        if(listLoaded.isEmpty()) {
            return "No information was saved in the system because the json file is empty.";
        } else {

            int n = listLoaded.getSize();

            int informationSaved = 0;
            String msg = "The information Saved in the system are the following:\n\n";

            for (int i = 0; i < n; i++) {
                if (list.search(listLoaded.get(i)) == null) {
                    System.out.println("entre");
                    list.add(listLoaded.get(i));
                    informationSaved++;
                    msg += informationSaved + ". " + listLoaded.get(i).toString() + "\n";
                }
            }

            return msg;
        }
    }



    public String getRoutesString() {
        return routes.getData();
    }



    public String searchSecurityIncident(String incidentID) {
        sortIncidentsByID();

        String msg="No incident found with that ID";
        boolean isFound = false;

        if (securityIncidents.isEmpty()){
            msg= "\n\nThere Are not incidents saved in the system. please, add or load some incidents to do the search.\n\n";
        } else {

            int left = 0;
            int right = securityIncidents.getSize() - 1;

            while (left <= right && !isFound) {
                int mid = left + (right - left) / 2;

                SecurityIncident midIncident = securityIncidents.get(mid);

                if (midIncident.getId().compareTo(incidentID) == 0) {
                    msg = midIncident.toString();
                    isFound = true;
                } else if (midIncident.getId().compareTo(incidentID) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return msg;
    }



    public String searchDriver(String driverName) {
        sortDriversByName();

        String msg="No driver found with that name";
        boolean isFound = false;

        if (drivers.isEmpty()){
            msg= "\n\nThere Are not drivers saved in the system. please, add or load some drivers to do the search.\n\n";
        } else {

            int left = 0;
            int right = drivers.getSize() - 1;

            while (left <= right && !isFound) {
                int mid = left + (right - left) / 2;

                Driver midDriver = drivers.get(mid);

                if (midDriver.getName().compareTo(driverName) == 0) {
                    msg = midDriver.toString();
                    isFound = true;
                } else if (midDriver.getId().compareTo(driverName) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return msg;
    }

    public String sortIncidentsByID(){

        Comparator<SecurityIncident> comparator = (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        };

        return securityIncidents.sortList(comparator);

    }


    public String searchBestRoute() {
        sortRouteByTravelTime();

        if(routes.isEmpty()) {
            return "There are no routes registered yet!";
        }
        return routes.getFirst().getData().toString();
    }


    public String sortDriversByName() {
        return drivers.sortList();
    }

    public String sortRouteByTravelTime(){
        return routes.sortList();
    }

    public String sortIncidentsByDateAndTime() {
        return securityIncidents.sortList();
    }


    public TypeOfIncident convertToTypeOfIncident(String typeString) {
        TypeOfIncident typeOfIncident = null;

        for(TypeOfIncident type:TypeOfIncident.values()) {
            if(typeString.equals(type.toString())) {
                typeOfIncident = type;
            }
        }

        return typeOfIncident;
    }


    public Date convertToDate(String dateString) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date localDate = null;

        try {
            localDate = dateFormat.parse(dateString);

        } catch (ParseException e) {
        }

        return localDate;
    }


    public IncidentStatus convertToIncidentStatus(String statusString) {
        IncidentStatus incidentStatus = null;

        for(IncidentStatus status:IncidentStatus.values()) {
            if(statusString.equals(status.toString())) {
                incidentStatus = status;
            }
        }

        return incidentStatus;
    }

    public DriverStatus convertToDriverStatus(String statusString) {
        DriverStatus driverStatus = null;

        for(DriverStatus status:DriverStatus.values()) {
            if(statusString.equals(status.toString())) {
                driverStatus = status;
            }
        }

        return driverStatus;
    }

    public boolean thereAreRoutesCreated() {
        return !routes.isEmpty();
    }

    public String getRoutesToString() {
        return routes.getData();
    }

    public Route searchRouteByID(String routeID) {
        int n = routes.getSize();
        Route routeFind = null;
        boolean routeIsFind = false;

        for(int i=0; i<n && !routeIsFind ;i++) {
            if(routes.get(i).getId().equalsIgnoreCase(routeID)) {
                routeFind = routes.get(i);
                routeIsFind = true;
            }
        }

        return routeFind;
    }

    public String printRoute() {
        return routes.positionLast().toString();
    }

    public int getRoutesSize() {
        return routes.getSize();
    }

}

