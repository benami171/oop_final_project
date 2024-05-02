package Flights_Management_System.SearchStrategies;

import Flights_Management_System.Flight;
import java.util.ArrayList;

public class SearchByDest implements SearchStrategy{
    @Override
    public void search(ArrayList<Flight> flights, String searchRange) {
        int number = 0;
        for (Flight flight : flights) {
            if (flight.getDestination().equalsIgnoreCase(searchRange)) {
                // printing all the flights that are between the given range in a nice way
                number++;
                System.out.println(number + ". " + flight);
            }
        }
        if (number == 0) {
            System.out.println("No flights found to the specified destination.");
        }
    }
}
