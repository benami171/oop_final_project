package Flights_Management_System;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Flight {
    private final String flightId;
    private Company owner;
    private static final ArrayList<String> flightIds = new ArrayList<>();
    private final ArrayList<Worker> flightCrew ;
    private final ArrayList<Customer> flightPassengers; // all the passengers on the flight who have booked
    private final ArrayList<Observer> flightSubscribers; // all the people who have subscribed to the flights newsletter
    private LocalDateTime departureDate; // Changed from String to LocalDateTime
    private double price; // in dollars
    private final String depLocation;
    private final String Destination;
    private int maxSeats;
    private String flightStatus = "On Time";

    public Flight(String flightId, String depLocation, String Destination, String departureDate, double price, int remainingSeats) {
        if (!flightIds.add(flightId)) {
            throw new IllegalArgumentException("Flight ID " + flightId + " already exists");
        }
        this.flightId = flightId;
        this.price = price;
        this.maxSeats = remainingSeats;
        this.depLocation = depLocation;
        this.Destination = Destination;
        this.flightCrew = new ArrayList<>();
        this.flightPassengers = new ArrayList<>();
        this.flightSubscribers = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.departureDate = LocalDateTime.parse(departureDate, formatter);    }




    public void addCrewMember(Worker worker) {
        flightCrew.add(worker);
    }

    public void removeCrewMember(Worker worker) {
        flightCrew.remove(worker);
    }

    public String getDestination() {
        return Destination;
    }

    public void delayFlight(int delayMinutes) {
        this.departureDate = this.departureDate.plusMinutes(delayMinutes);
    }


    // Updated getter for departureDate

    public String getDepartureDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.departureDate.format(formatter);
    }
    public Company getOwner() {
        return this.owner;
    }

    public String getOwnerName() {
        return this.owner.getName();
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public void addPassenger(Customer customer) {
        flightPassengers.add(customer);
    }

    public void removePassenger(Customer customer) {
        flightPassengers.remove(customer);
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public void flightBooked() {
        maxSeats--;
    }

    public void flightCancelled() {
        maxSeats++;
    }

    public int getRemainingSeats() {
        return maxSeats - flightPassengers.size();
    }

    public ArrayList<Customer> getFlightPassengers() {
        return flightPassengers;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getName() {
        return flightId;
    }


    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepLocation() {
        return depLocation;
    }

    @Override
    public String toString() {
        return String.format("Flight ID: %-10s | Departure Location: %-15s | Destination: %-15s | Flight Status: %-15s | Departure Date: %-20s | Price: %-10.2f | Remaining Seats: %-10d | Company Name: %s" ,
                flightId, depLocation, Destination, flightStatus, getDepartureDate(), price, getRemainingSeats(), owner.getName());
    }



}
