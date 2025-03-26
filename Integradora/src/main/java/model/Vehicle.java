package model;

public class Vehicle {
    private String licensePlate;
    private String location;
    private String brand;
    private String color;

    public Vehicle(String licensePlate, String location, String brand, String color) {
        this.licensePlate = licensePlate;
        this.location = location;
        this.brand = brand;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

