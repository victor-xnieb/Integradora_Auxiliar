package model;

public class Driver extends Person implements Comparable<Driver>, Identifiable {
    private Vehicle vehicle;
    private DriverStatus status;

    public Driver(String id, String name, String driverStatus) {
        super(id, name);
        this.status = convertToDriverStatus(driverStatus);
    }


    public DriverStatus convertToDriverStatus(String status) {
        if(status.equals("AVAILABLE")) {
            return DriverStatus.AVAILABLE;
        } else if (status.equals("ON_ROUTE")) {
            return DriverStatus.ON_ROUTE;
        } else {
            return null;
        }
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus driverStatus) {
        this.status = driverStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int compareTo(Driver driver) {
        return super.getName().compareTo(driver.getName());
    }

    @Override
    public String getIdentification() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() + ", ID: " + super.getId() +
                ", Status: " + status.toString() + ", Vehicle: " +
                vehicle;

    }
}

