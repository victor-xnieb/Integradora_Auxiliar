package model;

import java.util.UUID;

public class Route implements Comparable<Route>, Identifiable {
    private String id;
    private double distance;
    private int time;
    private String startPlace;
    private String endPlace;

    public Route(double distance, int time, String startPlace, String endPlace) {
        this.id = UUID.randomUUID().toString();;
        this.distance = distance;
        this.time = time;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
    }

    public Route(String id, double distance, int time, String startPlace, String endPlace) {
        this.id = id;
        this.distance = distance;
        this.time = time;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    @Override
    public int compareTo(Route route) {
        return time - route.getTime();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Distance: " + this.distance +
                " km , Travel time: " + this.time + " minutes, Start place: " +
                this.startPlace + ", End place: " + this.endPlace;
    }

    @Override
    public String getIdentification() {
        return id;
    }
}

