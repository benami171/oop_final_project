package Flights_Management_System.CompanyManagement;

import Flights_Management_System.*;
import Flights_Management_System.SearchStrategies.SearchByDate;
import Flights_Management_System.SearchStrategies.SearchByDest;
import Flights_Management_System.SearchStrategies.SearchByPrice;
import Flights_Management_System.SearchStrategies.SearchStrategy;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will handle all operations related to searching for flights such as searching by price, dates, or destination.
 * This design sticks to the Single Responsibility Principle by separating the concerns of
 * managing flights (Company class) and handling search operations (SearchManager class).
 */
public class SearchManager {
    private SearchStrategy searchStrategy;
    private final ArrayList<Flight> flights;




    public SearchManager(ArrayList<Flight> flights) {
        this.flights = flights;

    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void handleSearch() {
        Scanner scanner = new Scanner(System.in);
        String continueSearch;

        do {
            System.out.println("Welcome to " + flights.getFirst().getOwnerName() + " !\nPlease enter the criterion you want to search by: (Price/Dates/Destination).");
            String criterion;
            boolean validInput = false;
            while (!validInput) {
                criterion = scanner.nextLine();
                if (criterion.equalsIgnoreCase("Price")) {
                    setSearchStrategy(new SearchByPrice());
                    System.out.println("Enter the price range you want to search by in the following format: [minPrice,maxPrice] ");
                    validInput = true;
                } else if (criterion.equalsIgnoreCase("Dates")) {
                    setSearchStrategy(new SearchByDate());
                    System.out.println("Enter the dates range you want to search by in the following format: 'dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm'");
                    validInput = true;
                } else if (criterion.equalsIgnoreCase("Destination")) {
                    setSearchStrategy(new SearchByDest());
                    System.out.println("Enter the destination you want to search by: ");
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter 'Price', 'Dates' or 'Destination'");
                }
                if (validInput) {
                    String searchRange = scanner.nextLine();
                    try {
                        searchStrategy.search(flights, searchRange);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please re-enter the dates in the format 'dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm'");
                        validInput = false;
                    }
                }
            }
            System.out.println("Do you want to search for another flight? (yes/no)");
            continueSearch = scanner.nextLine();
        } while (continueSearch.equalsIgnoreCase("yes"));
    }
}