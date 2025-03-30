package model;

public class Driver extends Person implements Comparable<Driver>, Identifiable {
    private Vehicle vehicle;
    private DriverStatus status;

    public Driver(String id, String name, Vehicle vehicle, DriverStatus driverStatus) {
        super(id, name);
        this.vehicle = vehicle;
        this.status = driverStatus;
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
        return "Name: " + super.getName() + "\nID: " + super.getId() +
                "\nStatus: " + status.toString() + "\nVehicle: " +
                vehicle.toString();
    }
}

