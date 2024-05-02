package Flights_Management_System.SearchStrategies;

import Flights_Management_System.Flight;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SearchByDate implements SearchStrategy {
    @Override
    public void search(ArrayList<Flight> flights, String searchRange) {
        String[] dates = searchRange.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fromDate = LocalDateTime.parse(dates[0], formatter);
        LocalDateTime toDate = LocalDateTime.parse(dates[1], formatter);
        int number = 0;

        for (Flight flight : flights) {
            LocalDateTime flightDate = LocalDateTime.parse(flight.getDepartureDate(), formatter);
            if (flightDate.isAfter(fromDate) && flightDate.isBefore(toDate)) {
                number++;
                System.out.println(number + ". " + flight);
            }
        }
        if (number == 0) {
            System.out.println("No flights found in the specified date range.");
        }
    }
}