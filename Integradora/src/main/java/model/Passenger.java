package model;

public class Passenger extends Person implements Comparable<Passenger>, Identifiable {
    private Route assignedRoute;
    private String contact;

    public Passenger(String id, String name,Route route, String contact) {
        super(id, name);
        this.assignedRoute = route;
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Route getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(Route assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    @Override
    public int compareTo(Passenger passenger) {
        return 0;
    }

    @Override
    public String getIdentification() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "ID: " + super.getId() + "\nName: " + super.getName() +
                ",\nAssigned Route: " + this.assignedRoute.toString() + "\nContact: " + this.contact;
    }
}

