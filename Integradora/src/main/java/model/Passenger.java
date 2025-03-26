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

    @Override
    public int compareTo(Passenger passenger) {
        return 0;
    }

    @Override
    public String getIdentification() {
        return super.getId();
    }
}

