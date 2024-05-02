package Flights_Management_System.SearchStrategies;

import Flights_Management_System.Flight;

import java.util.ArrayList;

public interface SearchStrategy {
    void search(ArrayList<Flight> flights, String searchRange);
}
