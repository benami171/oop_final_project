package Flights_Management_System.CompanyManagement;

import Flights_Management_System.Company;
import Flights_Management_System.Flight;
import Flights_Management_System.Observer;
import Flights_Management_System.Subject;
import java.util.ArrayList;

public class NotificationsManager implements Subject {
    private final ArrayList<Observer> observers;
    private final Company myCompany;

    public NotificationsManager(Company myCompany, ArrayList<Observer> observers) {
        this.observers = observers;
        this.myCompany = myCompany;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!this.observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversDelay(Flight flight ,int minutes) {
        for (Observer observer : flight.getFlightPassengers()) {
            observer.update(observer.getName() + ", Flight " + flight.getFlightId() + " has been delayed by " + minutes + " minutes and her new departure time is " + flight.getDepartureDate());
        }
    }

    @Override
    public void notifyObserversCancellation(Flight flight) {
        for (Observer observer : flight.getFlightPassengers()) {
            observer.update(observer.getName() + ", Flight " + flight.getFlightId() + " has been cancelled");
        }
    }

    /**
     * This function notifies all the subscribers of the company that a sale has been created.
     * @param flight - the flight that is on sale
     * @param percentage - the percentage of the sale
     */
    @Override
    public void notifyObserversSale(Flight flight ,int percentage) {
        for (Observer observer : this.observers) {
            observer.update(observer.getName() + ", " + flight.getFlightId() + " is now on sale for " + percentage + "% off!");
        }
    }

    public void printObservers() {
        System.out.println("The observers list of " + myCompany.getName() + " is: ");
        for (Observer observer : observers) {
            System.out.println(observer.getName());
        }
    }
}