
package Flights_Management_System.CompanyManagement;

import Flights_Management_System.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will handle all operations related to flights such as booking, cancellation, and delay.
 *  as we want to maintain the single responsibility principle.
 */

public class FlightsManager {
    private final ArrayList<Flight> flights;
    private final ArrayList<Observer> observers;
    private final Company myCompany;



    public FlightsManager(Company myCompany, ArrayList<Flight> flights, ArrayList<Observer> observers) {
        this.flights = flights;
        this.observers = observers;
        this.myCompany = myCompany;
    }

    public Flight newFlight(String flightId, String from, String to, String departureDate, double price, int maxSeats, Company owner) {
        Flight flight = new Flight(flightId, from, to, departureDate, price, maxSeats);
        flight.setOwner(owner);
        flights.add(flight);
        AirPort.getInstance("Airport").addFlight(flight);
        return flight;
    }

    public void assignWorkerToFlight(Flight flight, Worker worker) {
        flight.addCrewMember(worker);
    }

    public int getFlightsCount() {
        return flights.size();
    }

    public void newSale(Flight flight, int percentage, Company company) {
        flight.setPrice(flight.getPrice() * (1 - percentage / 100.0));
        company.notifyObserversSale(flight, percentage);
    }

    public void bookFlight(Flight flight, Customer customer) {
        if (flight.getRemainingSeats() > 0) {
            flight.flightBooked(); // decrease the remaining seats
            flight.addPassenger(customer); // add the customer to the flight
            customer.getBookedFlights().add(flight); // add the flight to the customer's booked flights

            Scanner scanner = new Scanner(System.in);
            String response;
            do {
                System.out.println("Hey " + customer.getName() + " your flight was booked successfully!\nWould you like to subscribe to " + flight.getOwner().getName() + "  newsletter? (yes/no)");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                    System.out.println("Invalid input, please enter 'yes' or 'no'");
                }
            } while (!response.equals("yes") && !response.equals("no"));
            if (response.equals("yes")) {
                flight.getOwner().addObserver(customer);
            }
        } else {
            System.out.println("Flight is full, could not book flight for " + customer.getName());
        }
    }

    public void cancelBooking(Flight flight, Customer customer) {
        if (flight == null) {
            System.out.println("Flight does not exist");
            return;
        }
        // Check if this company owns the flight
        if (flights.contains(flight)) {
            flight.removePassenger(customer);
            customer.getBookedFlights().remove(flight);
            flight.flightCancelled();
        } else {
            System.out.println("Flight not found in this company");
        }
    }

    public void cancelFlight(Flight flight) {
        if (flight == null) {
            System.out.println("Flight does not exist");
            return;
        }
        // Check if this company owns the flight
        if (flights.contains(flight)) {
            flight.getFlightPassengers().forEach(passenger -> passenger.getBookedFlights().remove(flight));
            AirPort.getInstance("Airport").removeFlight(flight);
            flight.getOwner().notifyObserversCancellation(flight);
            flights.remove(flight);
        } else {
            System.out.println("Flight not found in this company");
        }
    }

    public void delayFlight(Flight flight, int minutes) {
        if (flight == null) {
            System.out.println("Flight does not exist");
            return;
        }
        // Check if this company owns the flight
        if (flights.contains(flight)) {
            flight.delayFlight(minutes);
            flight.setFlightStatus("Delayed");
            flight.getOwner().notifyObserversDelay(flight, minutes);
        } else {
            System.out.println("Flight not found in this company");
        }
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void printCompanyFlights() {
        int number = 0;
        System.out.println("Company name: " + myCompany.getName());
        for (Flight flight : flights) {
            number++;
            System.out.println(number + ". " + flight);
        }
    }


}