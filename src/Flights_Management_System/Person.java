package Flights_Management_System;

import java.util.ArrayList;
import java.util.List;


public abstract class Person implements Observer{
    private final String name;
    private final String email;
    private final int phone;
    private final int personalID;
    private final ArrayList<Flight> bookedFlights = new ArrayList<>();

    public Person(String name, String email, int phone, int personalID) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.personalID = personalID;
    }

    // a list of flights that the person has booked
    public ArrayList<Flight> getBookedFlights() {
        return bookedFlights;
    }

    public void update(String message) {
        System.out.println(message);
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public int getPersonalID() {
        return personalID;
    }

    public String getName() {
        return name;
    }


}
