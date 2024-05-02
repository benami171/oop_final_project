package Flights_Management_System.SearchStrategies;

import Flights_Management_System.Flight;
import java.util.ArrayList;

public class SearchByPrice implements SearchStrategy{
    @Override
    public void search(ArrayList<Flight> flights, String searchRange) {
        String[] rangeOfPrices = searchRange.split(",");
        int from = Integer.parseInt(rangeOfPrices[0]);
        int to = Integer.parseInt(rangeOfPrices[1]);
        int number = 0;
        for (Flight flight : flights) {
            if (flight.getPrice() >= from && flight.getPrice() <= to) {
                // printing all the flights that are between the given range in a nice way
                number++;
                System.out.println(number + ". " + flight);
            }
        }
        if (number == 0) {
            System.out.println("No flights found in the specified price range.");
        }
    }
}
