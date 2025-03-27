package ui;

import model.Controller;
import java.util.Scanner;
import exceptions.*;

public class Main {

    private Controller controller;
    private Scanner sc;

    Main(){
        controller = new Controller();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("\nWelcome to the application\n");
        main.menu();
    }

    public void menu(){

        boolean leave = false;

        while(leave == false) {
            System.out.println("\n\n****************** MENU ******************\n\n" +
                    "Please choose an option.\n\n"+
                    "1.Routes managment.\n" +
                    "2.Security Incidents managment.\n" +
                    "3.Passenger managment.\n" +
                    "4.Drivers managment.\n" +
                    "5.Sort routes by travel time.\n" +
                    "6.Sort incidents by date/time of the report\n" +
                    "7.Search incident by ID.\n" +
                    "8.Search driver by name.\n" +
                    "9.Search the best route based in the travel time.\n" +
                    "10.Exit.");

            int option= sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    routesManagment();
                    break;
                case 2:
                    securityIncidentsManagment();
                    break;
                case 3:
                    passengerManagment();
                    break;
                case 4:
                    driversManagment();
                    break;
                case 5:
                    sortRoutesByTravelTime();
                    break;
                case 6:
                    sortIncidentsByDateAndTimeOfReport();
                    break;
                case 7:
                    searchIncident();
                    break;
                case 8:
                    searchDriver();
                    break;
                case 9:
                    searchBestRouteBasedInTravelTime();
                    break;
                case 10:
                    System.out.println("Leaving...");
                    leave = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again: \n");
            }
        }
    }

    public void routesManagment(){

        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Enter a route Manually.\n"+
                    "2. Save routes.\n" +
                    "3. Load routes.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                enterRouteManually();
            } else if (option ==2){
                saveRoutes();
            } else if(option == 3) {
                loadRoutes();
            } else {
                System.out.println("Enter a valid option.\n\n");
            }

        } while (option!=1 && option!=2 && option!=3);

    }

    public void enterRouteManually() {

        System.out.print("Enter the route ID: ");
        String id = sc.nextLine();

        System.out.print("Enter the distance (km): ");
        double distance = sc.nextDouble();

        System.out.print("Enter the estimated time (minutes): ");
        int time = sc.nextInt();
        sc.nextLine(); // Consume the pending newline

        System.out.print("Enter the starting place: ");
        String startPlace = sc.nextLine();

        System.out.print("Enter the destination: ");
        String endPlace = sc.nextLine();

        try {
        controller.addRoute( controller.createRoute(id,distance,time,startPlace,endPlace));
        System.out.println("\n\nRoute created and added to the system\n\n");

        } catch (RouteAlreadyExistsException e) {
            System.out.println("\n\nYou have already added this route before.\n\n");
        }

    }

    public void saveRoutes() {
        /*System.out.println("Enter the name of the file where you want to save the routes in Json Format. Example: routes.json");
        String fileName = sc.nextLine();*/

        System.out.println(controller.saveRoutesToJson());
    }

    public void loadRoutes() {

        /*System.out.println("Enter the name of the file where you have the routes in Json Format. Example: routes.json");
        String fileName = sc.nextLine();*/

        System.out.println( controller.addObjectsLoaded( controller.loadRoutesFromJson(), controller.getRoutes() ) );
        System.out.println(controller.getRoutesString());
    }

    public void securityIncidentsManagment(){

        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Add a security incident manually\n"+
                    "2. Save Security Incidents.\n" +
                    "3. Load Security Incidents.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                enterSecurityIncidentManually();
            } else if(option == 2) {
                saveSecurityIncidents();
            } else if(option == 3){
                loadSecurityIncidents();
            } else {
                System.out.println("\n\nEnter a valid option.\n\n");
            }

        } while (option!=1 && option!=2 && option!=3) ;

    }

    public void enterSecurityIncidentManually() {
        System.out.print("Enter the incident ID: ");
        String id = sc.nextLine();

        String type = "";
        boolean methodflag = false;

        do {
            System.out.print("Enter the type of incident (THEFT, ACCIDENT, FIRE, OTHER): ");
            type = sc.nextLine().toUpperCase();

            if(controller.convertToTypeOfIncident(type)==null) {
                System.out.println("\nEnter a valid option.\n\n");
            } else {
                methodflag = true;
            }

        } while (!methodflag);

        System.out.print("Enter the location: ");
        String location = sc.nextLine();

        String reportDateTime = "";
        methodflag = false;

        do {
            System.out.print("Enter the report date and time (YYYY-MM-DD HH:MM): ");
            reportDateTime = sc.nextLine();

            if(controller.convertToDate(reportDateTime)==null) {
                System.out.println("\n\nInvalid format. Enter the date and time in the format indicated.\n\n");
            } else {
                methodflag = true;
            }
        } while (!methodflag);

        System.out.print("Enter a brief description: ");
        String description = sc.nextLine();

        String status = "";
        methodflag = false;

        do {
            System.out.print("Enter the incident status (PENDING, IN_PROCESS, RESOLVED): ");
            status = sc.nextLine().toUpperCase();

            if(controller.convertToIncidentStatus(status)==null) {
                System.out.println("\n\nEnter a valid option.\n\n");
            } else {
                methodflag = true;
            }
        } while (!methodflag);

        try{
            controller.addSecurityIncident(controller.createSecurityIncident(
                    id,
                    controller.convertToTypeOfIncident(type),
                    location,
                    controller.convertToDate(reportDateTime),
                    description,
                    controller.convertToIncidentStatus(status)
            ));

            System.out.println("\n\nSecurity incident created and added to the system\n\n");

        } catch (IncidentAlreadyExistsException e) {
            System.out.println("\n\nYou have already added this security incident before.\n\n");
        }


    }

    public void saveSecurityIncidents() {
        /*System.out.println("Enter the name of the file where you want to save the security incidents in Json Format. Example: incidents.json");
        String fileName = sc.nextLine();*/

        System.out.println(controller.saveSecurityIncidentsToJson());
    }

    public void loadSecurityIncidents() {
        /*System.out.println("Enter the name of the file where you have the security incidents in Json Format. Example: incidents.json");
        String fileName = sc.nextLine();*/

        System.out.println( controller.addObjectsLoaded( controller.loadIncidentsFromJson(), controller.getSecurityIncidents() ) );
    }

    public void passengerManagment(){
        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Add a passenger manually.\n"+
                    "2. Save passengers.\n" +
                    "3. Load passengers.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                enterPassengerManually();
            } else if(option == 2){
                savePassengers();
            } else if(option == 3){
                loadPassengers();
            } else {
                System.out.println("\n\nEnter a valid option.\n\n");
            }

        } while (option!=1 && option!=2 && option!=3);
    }


    public void enterPassengerManually(){
        System.out.print("Enter the passenger id:");
        String id = sc.nextLine();

        System.out.print("Enter the passenger's name:");
        String name = sc.nextLine();

        if(!controller.thereAreRoutesCreated()) {
            System.out.println("\n\nThere are no routes saved in the system to assign to the passenger." +
                    " Please add the route you want to assign to this passenger.\n\n");

            enterRouteManually();

        }

        System.out.println("\n\nRoutes saved in the system:\n\n");
        System.out.println(controller.getRoutesToString());

        String routeID;
        boolean methodFlag = false;

        do {
            System.out.print("Enter the id of the route the passenger has been assigned to: ");
            routeID = sc.nextLine();

            if(controller.searchRouteByID(routeID)==null) {
                System.out.println("\n\nEnter the id of one of the lists shown above\n\n");
            } else {
                methodFlag = true;
            }
        } while (!methodFlag);

        System.out.print("Enter the contact of the passenger:");
        String contact = sc.nextLine();

        try {
            controller.addPassenger( controller.createPassenger(
                   id,
                   name,
                   controller.searchRouteByID(routeID),
                   contact
            ));

            System.out.println("\n\nPassenger created and added to the system\n\n");
        } catch (PassengerAlreadyExistsException e) {

            System.out.println("\n\nYou have already added this passenger before.\n\n");
        }


    }

    public void savePassengers() {
        /*System.out.println("Enter the name of the file where you want to save the passengers in Json Format. Example: passengers.json");
        String fileName = sc.nextLine();*/

        System.out.println(controller.savePassengersToJson());
    }

    public void loadPassengers() {
        /*System.out.println("Enter the name of the file where you have the passengers in Json Format. Example: passengers.json");
        String fileName = sc.nextLine();*/

        System.out.println( controller.addObjectsLoaded( controller.loadPassengersFromJson(), controller.getPassengers() ) );
    }

    public void driversManagment(){
        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Add a driver manually.\n" +
                    "2. Save drivers.\n" +
                    "3. load drivers.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                enterDriverManually();
            } else if (option == 2) {
                saveDrivers();
            } else if (option == 3) {
                loadDrivers();
            } else {
                System.out.println("\n\nEnter a valid option.\n\n");
            }

        } while (option!=1 && option!=2 && option!=3);

    }

    public void enterDriverManually(){
        System.out.print("Enter the id of the driver:");
        String id = sc.nextLine();

        System.out.print("Enter the name of the driver:");
        String name = sc.nextLine();

        System.out.print("Enter the license plate of the vehicle to which the driver is assigned:");
        String vehicleLicensePlate = sc.nextLine();

        System.out.println("Enter the brand of the vehicle assigned to the driver:");
        String vehicleBrand = sc.nextLine();

        String driverStatus = "";
        boolean methodFlag = false;

        do {
            System.out.print("Enter the status of the driver (AVAILABLE,ON_ROUTE) : ");
            driverStatus = sc.nextLine().toUpperCase();

            if(controller.convertToDriverStatus(driverStatus)==null) {
                System.out.println("\n\nEnter a valid option.\n\n");
            } else {
                methodFlag = true;
            }

        } while (!methodFlag);

        try {
            controller.addDriver( controller.createDriver(
                    id,
                    name,
                    controller.createVehicle(vehicleLicensePlate,vehicleBrand),
                    controller.convertToDriverStatus(driverStatus)
            ));

            System.out.println("\n\nDriver created and added to the system\n\n");
        } catch ( DriverAlreadyExistsException e) {
            System.out.println("\n\nYou have already added this passenger before.\n\n");
        }

    }


    public void saveDrivers() {
        /*System.out.println("Enter the name of the file where you want to save the drivers in Json Format. Example: drivers.json");
        String fileName = sc.nextLine();*/

        System.out.println(controller.saveDriversToJson());
    }

    public void loadDrivers() {
        /*System.out.println("Enter the name of the file where you have the drivers in Json Format. Example: drivers.json");
        String fileName = sc.nextLine();*/

        System.out.println( controller.addObjectsLoaded( controller.loadDriversFromJson(), controller.getDrivers() ) );
    }

    public void sortRoutesByTravelTime(){
        System.out.println(controller.sortRouteByTravelTime());
    }

    public void sortIncidentsByDateAndTimeOfReport(){
        String message = controller.sortIncidentsByDateAndTime();

        if(message.isEmpty()) {
            System.out.println("\n\nThere are not incidents saved in the system. Please add it manually or load a file" +
                    "with incidents.\n\n");
        } else {
            System.out.println(message);
        }

    }

    public void searchIncident(){

        System.out.println("Enter the ID of the security incident you want to search");
        String incidentID = sc.nextLine();

        System.out.println(controller.searchSecurityIncident(incidentID));

    }

    public void searchDriver() {

        System.out.println("Enter the name of the driver you want to search");
        String driverName = sc.nextLine();

        System.out.println(controller.searchDriver(driverName));

    }

    public void searchBestRouteBasedInTravelTime() {
        if(controller.thereAreRoutesCreated()) {
            System.out.println("The route with the less travel time is:\n" + controller.searchBestRoute());
        } else {
            System.out.println("\n\nThere Are not routes saved in the system. please, add or load some routes to do the search.\n\n");
        }
    }
}

