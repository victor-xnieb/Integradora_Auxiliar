package ui;

import model.Controller;
import java.util.Scanner;

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
            System.out.println("****************** MENU ******************\n\n" +
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
                    securityIncidents();
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
                    "2. Load routes.\n" +
                    "3. Save routes.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                enterRouteManually();
            } else if (option ==2){
                loadRoutes();
            } else {
                saveRoutes();
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


        if(controller.addRoute( controller.createRoute(id,distance,time,startPlace,endPlace))) {
            System.out.println("Route created and added to the system");
        } else {
            System.out.println("Route couln't be added in the system");
        }

    }

    public void loadRoutes() {

        System.out.println("Enter the name of the file where you have the routes in Json Format. Example: routes.json");
        String fileName = sc.nextLine();

        System.out.println( controller.addObjectsLoaded( controller.loadRoutesFromJson(fileName), controller.getRoutes() ) );
        System.out.println(controller.getRoutesString());
    }

    public void saveRoutes() {
        System.out.println("Enter the name of the file where you want to save the routes in Json Format. Example: routes.json");
        String fileName = sc.nextLine();

        System.out.println(controller.saveRoutesToJson(fileName));
    }

    public void securityIncidents(){

        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Load Security Incidents.\n" +
                    "2. Save Security Incidents.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                loadSecurityIncidents();
            } else {
                saveSecurityIncidents();
            }
        } while (option!=1 && option!=2);
    }

    public void loadSecurityIncidents() {
        System.out.println("Enter the name of the file where you have the security incidents in Json Format. Example: incidents.json");
        String fileName = sc.nextLine();

        System.out.println( controller.addObjectsLoaded( controller.loadIncidentsFromJson(fileName), controller.getSecurityIncidents() ) );
    }

    public void saveSecurityIncidents() {
        System.out.println("Enter the name of the file where you want to save the security incidents in Json Format. Example: incidents.json");
        String fileName = sc.nextLine();

        System.out.println(controller.saveSecurityIncidentsToJson(fileName));
    }

    public void passengerManagment(){
        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Load passengers.\n" +
                    "2. Save passengers.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                loadPassengers();
            } else {
                savePassengers();
            }
        } while (option!=1 && option!=2);
    }

    public void loadPassengers() {
        System.out.println("Enter the name of the file where you have the passengers in Json Format. Example: passengers.json");
        String fileName = sc.nextLine();

        System.out.println( controller.addObjectsLoaded( controller.loadPassengersFromJson(fileName), controller.getPassengers() ) );
    }

    public void savePassengers() {
        System.out.println("Enter the name of the file where you want to save the passengers in Json Format. Example: passengers.json");
        String fileName = sc.nextLine();

        System.out.println(controller.savePassengersToJson(fileName));
    }

    public void driversManagment(){
        int option = 0;

        do {
            System.out.println("\nWhat do you want to do?\n\n" +
                    "1. Load drivers.\n" +
                    "2. Save drivers.\n");

            option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                loadDrivers();
            } else {
                saveDrivers();
            }
        } while (option!=1 && option!=2);

    }

    public void loadDrivers() {
        System.out.println("Enter the name of the file where you have the drivers in Json Format. Example: drivers.json");
        String fileName = sc.nextLine();

        System.out.println( controller.addObjectsLoaded( controller.loadDriversFromJson(fileName), controller.getDrivers() ) );
    }

    public void saveDrivers() {
        System.out.println("Enter the name of the file where you want to save the drivers in Json Format. Example: drivers.json");
        String fileName = sc.nextLine();

        System.out.println(controller.saveDriversToJson(fileName));
    }

    public void sortRoutesByTravelTime(){
        System.out.println(controller.sortRouteByTravelTime());
    }

    public void sortIncidentsByDateAndTimeOfReport(){
        System.out.println(controller.sortIncidentsByDateAndTime());
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
        System.out.println("The route with the less travel time is:\n" + controller.searchBestRoute());
    }
}

