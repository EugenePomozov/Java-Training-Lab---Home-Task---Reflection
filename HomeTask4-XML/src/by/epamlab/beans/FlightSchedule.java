package by.epamlab.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSchedule {
    private String airline;
    private Aircraft aircraft;
    private Date dateOrigin;
    private Date dateDestination;
    private String clas;
    private double ticketCost;
    SimpleDateFormat dateFormat = new SimpleDateFormat("y-MM-dd");

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getDateOrigin() {
        return dateFormat.format(dateOrigin);
    }

    public void setDateOrigin(Date dateOrigin) {
        this.dateOrigin = dateOrigin;
    }

    public String getDateDestination() {
        return dateFormat.format(dateDestination);
    }

    public void setDateDestination(Date dateDestination) {
        this.dateDestination = dateDestination;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clazz) {
        this.clas = clazz;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }
}
