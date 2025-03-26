package model;

import structures.DoublyLinkedList;
import util.FileManager;

import java.util.Comparator;

public class Controller {

    private DoublyLinkedList<Route> routes;
    private DoublyLinkedList<SecurityIncident> securityIncidents;
    private DoublyLinkedList<Passenger> passengers;
    private DoublyLinkedList<Driver> drivers;

    private FileManager fileManager;

    public Controller() {
        System.out.println("Hola controller");
        this.routes = new DoublyLinkedList<>();
        this.securityIncidents = new DoublyLinkedList<>();
        this.passengers = new DoublyLinkedList<>();
        this.drivers = new DoublyLinkedList<>();
        this.fileManager = new FileManager();

    }


    //Routes Managment
    public String saveRoutesToJson(String fileName){
        return fileManager.saveRoutes(routes);
    }

    public DoublyLinkedList<Route> loadRoutesFromJson(String fileName) {
        return fileManager.loadRoutes(fileName);
    }

    public Route createRoute(String id, double distance, int time,String startPlace,String endPlace) {
        return new Route(id,distance,time,startPlace,endPlace);
    }

    public boolean addRoute(Route route) {
        return addObject(route,routes);
    }

    //Incidents Managment
    public String saveSecurityIncidentsToJson(String fileName) {
        return fileManager.saveIncidents(fileName, securityIncidents);
    }

    public String savePassengersToJson(String fileName) {
        return fileManager.savePassengers(fileName, passengers);
    }

    //public SecurityIncident createSecurityIncident()

    public <T extends Comparable<T> & Identifiable> boolean addObject(T object, DoublyLinkedList<T> list) {
        boolean isAdded = false;

        if(list.search(object) == null) {
            isAdded = list.add(object);
        }

        return isAdded;
    }

    public String saveDriversToJson(String fileName) {
        return fileManager.saveDrivers(fileName, drivers);
    }


    public DoublyLinkedList<SecurityIncident> loadIncidentsFromJson(String fileName) {
        return fileManager.loadSecurityIncidents(fileName);
    }


    public DoublyLinkedList<Passenger> loadPassengersFromJson(String fileName) {
        return fileManager.loadPassengers(fileName);
    }


    public DoublyLinkedList<Driver> loadDriversFromJson(String fileName) {
        return fileManager.loadDrivers(fileName);
    }


    public <T extends Comparable<T> & Identifiable> String addObjectsLoaded(DoublyLinkedList<T> listLoaded, DoublyLinkedList<T> list) {
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



    public boolean addSecurityIncident(SecurityIncident incident) {
        return securityIncidents.add(incident);
    }


    public boolean addPassenger(Passenger passenger) {
        return passengers.add(passenger);
    }


    public boolean addDriver(Driver driver) {
        return drivers.add(driver);
    }


    public String searchSecurityIncident(String incidentID) {
        sortIncidentsByID();

        String msg="";
        boolean isFound = false;

        if (securityIncidents.isEmpty()){
            msg= "The list does not have information";
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

        String msg="";
        boolean isFound = false;

        if (drivers.isEmpty()){
            msg= "The list does not have information";
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
        return routes.getFirst().getData().toString();
    }

    public DoublyLinkedList<Route> getRoutes() {
        return routes;
    }

    public DoublyLinkedList<SecurityIncident> getSecurityIncidents() {
        return securityIncidents;
    }

    public DoublyLinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public DoublyLinkedList<Driver> getDrivers() {
        return drivers;
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


}

