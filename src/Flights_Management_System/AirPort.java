package Flights_Management_System;
import Flights_Management_System.SearchStrategies.SearchStrategy;
import java.util.ArrayList;


/**
 * This class represents an airport that contains flights and companies.
 * This class could serve as a central hub for managing all flights and companies in our system.
 */
public class AirPort {
    private final ArrayList<Flight> flights = new ArrayList<>();
    private final ArrayList<Company> companies = new ArrayList<>();
    private final ArrayList<Worker> workers = new ArrayList<>();
    private String name = null;
    private static AirPort instance = null;
    private SearchStrategy searchStrategy;

    private AirPort(String name) {
        this.name = name;
    }

    public static AirPort getInstance(String name) {
        if (instance == null) {
            instance = new AirPort(name);
        }
        return instance;
    }

    /**
     * This method delays a flight by a given number of minutes.
     * we want the airport to have the ability to contact a company and cancel its flight,
     * for example in cases of bad weather or technical issues.
     * we delegate the act of delaying a flight is a responsibility of the company that owns the flight,
     * which will also delegate that to its FlightsManager.
     * @param flight - the flight to be delayed
     * @param minutes - the number of minutes to delay the flight by
     */
    public void delayFlight(Flight flight, int minutes) {
        // Check if the flight exists in the airport
        if (flights.contains(flight)) {
            // Delegate the responsibility to delay the flight to the flight's owner (Company)
            flight.getOwner().delayFlight(flight, minutes);
        } else {
            System.out.println("Flight not found in this airport");
        }
    }

    /**
     * This method cancels a flight.
     * as with delaying a flight, we want the airport to have the ability to contact a company and cancel its flight.
     * we delegate the act of cancelling a flight is a responsibility of the company that owns the flight,
     * which will also delegate that to its FlightsManager.
     * @param flight - the flight to be cancelled
     */
    public void cancelFlight(Flight flight) {
        // Check if the flight exists in the airport
        if (flights.contains(flight)) {
            // Delegate the responsibility to cancel the flight to the flight's owner (Company)
            flight.getOwner().cancelFlight(flight);
        } else {
            System.out.println("Flight not found in this airport");
        }
    }

    /**
     * This method searches for flights based on the given search strategy and search range.
     * @param searchStrategy - the search strategy to be used
     * @param searchRange - the search range to be used
     */
    public void searchFlights(SearchStrategy searchStrategy, String searchRange) {
        setSearchStrategy(searchStrategy);
        searchStrategy.search(flights, searchRange);
        System.out.println();
    }


    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    public void removeCompany(Company company) {
        companies.remove(company);
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public String getName() {
        return this.name;
    }

    public void printAllFlights() {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void printAllCompanies() {
        for (Company company : companies) {
            System.out.println(company);
        }
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
}