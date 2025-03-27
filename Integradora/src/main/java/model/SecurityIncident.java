package model;


import model.Identifiable;
import java.util.Date;
import java.text.SimpleDateFormat;


public class SecurityIncident implements Comparable<SecurityIncident> , Identifiable {
    private String id;
    private TypeOfIncident type;
    private String location;
    private Date reportDateTime;
    private String description;
    private IncidentStatus status;

    public SecurityIncident(String id, TypeOfIncident type, String location,Date reportDateTime, String description, IncidentStatus status) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.reportDateTime = reportDateTime;
        this.description = description;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getReportDateTime() {
        return reportDateTime;
    }

    public void setReportDateTime(Date reportDateTime) {
        this.reportDateTime = reportDateTime;
    }

    @Override
    public int compareTo(SecurityIncident incident) {
        return this.reportDateTime.compareTo(incident.getReportDateTime());
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public String toString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Solo horas y minutos

        String date = dateFormat.format(reportDateTime); // Extrae la fecha
        String time = timeFormat.format(reportDateTime);

        return "ID: " + this.id + ", Type of Incident: " + type.toString() +
                ", Location: " + location + ", Report date: " + date +
                ", Report time: " + time + ", Description: " + description +
                ", Incident status: " + status.toString();
    }
}

