package model;

import java.time.LocalDate;
import java.time.LocalTime;
import model.Identifiable;


public class SecurityIncident implements Comparable<SecurityIncident> , Identifiable {
    private String id;
    private TypeOfIncident type;
    private String location;
    private LocalDate reportDate;
    private LocalTime reportTime;
    private String description;
    private IncidentStatus status;

    public SecurityIncident(String id, TypeOfIncident type, String location, String description, IncidentStatus status) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.reportDate = LocalDate.now();
        this.reportTime = LocalTime.now();
        this.description = description;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalTime reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public int compareTo(SecurityIncident incident) {
        int compare = this.reportDate.compareTo(incident.getReportDate());

        if(compare==0) {
            compare = this.reportTime.compareTo(incident.getReportTime());
        }

        return compare;
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Type of Incident: " + type.toString() +
                ", Location: " + location + ", Report date: " + reportDate.toString() +
                ", Report time: " + reportTime + ", Description: " + description +
                ", Incident status: " + status.toString();
    }
}

