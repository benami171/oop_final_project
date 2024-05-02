package Flights_Management_System;

import java.util.*;

import Flights_Management_System.CompanyManagement.*;


public class Company implements Component, Subject {
    private final String name;
    private Company owner = null;
    private final ArrayList<Flight> flights;
    private final ArrayList<Worker> workers;
    private final FlightsManager flightsManager;
    private final SearchManager searchManager;
    private final WorkersManager workersManager;
    private final ArrayList<Observer> observers;
    private final ArrayList<Company> subCompanies;
    private final SubCompaniesManager subCompaniesManager;
    private final NotificationsManager notificationsManager;


    public Company(String name) {
        this.name = name;
        this.subCompanies = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.flightsManager = new FlightsManager(this, flights, observers);
        this.searchManager = new SearchManager(flights);
        this.subCompaniesManager = new SubCompaniesManager(this, subCompanies);
        this.notificationsManager = new NotificationsManager(this,observers);
        this.workersManager = new WorkersManager(this, workers);
    }

    /**************** START OF FUNCTIONS DELEGATED TO FLIGHT MANAGER ****************/

    public Flight newFlight(String flightId, String from, String to, String departureDate, double price, int maxSeats) {
        return flightsManager.newFlight(flightId, from, to, departureDate, price, maxSeats, this);
    }

    public void bookFlight(Flight flight, Customer customer) {
        flightsManager.bookFlight(flight, customer);
    }

    public void cancelBooking(Flight flight, Customer customer) {
        flightsManager.cancelBooking(flight, customer);
    }

    public void cancelFlight(Flight flight) {
        flightsManager.cancelFlight(flight);
    }

    public void delayFlight(Flight flight, int minutes) {
        flightsManager.delayFlight(flight, minutes);
    }

    public void printCompanyFlights() {
        flightsManager.printCompanyFlights();
    }

    public ArrayList<Flight> getFlights() {
        return flightsManager.getFlights();
    }


    public void newSale(Flight flight, int percentage) {
        this.flightsManager.newSale(flight, percentage, this);
    }

    /**************** END OF FUNCTIONS DELEGATED TO FLIGHT MANAGER ****************
     **************** START OF FUNCTIONS DELEGATED TO SUB COMPANY MANAGER ****************/

    @Override
    public void addSubCompany(Company company) {
        this.subCompaniesManager.addSubCompany(company);
    }

    @Override
    public void removeSubCompany(Company company) {
        this.subCompaniesManager.removeSubCompany(company);
    }

    @Override
    public String getSubCompaniesNames() {
        return this.subCompaniesManager.getSubCompaniesNames();
    }


    /**************** END OF FUNCTIONS DELEGATED TO SUB COMPANY MANAGER ****************
     **************** START OF FUNCTIONS DELEGATED TO SEARCH MANAGER ****************/

    public void search() {
        this.searchManager.handleSearch();
    }

    /**************** END OF FUNCTIONS DELEGATED TO SEARCH MANAGER ****************
     **************** START OF FUNCTIONS DELEGATED TO NOTIFICATION MANAGER ****************/
    @Override
    public void addObserver(Observer observer) {
        this.notificationsManager.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.notificationsManager.removeObserver(observer);
    }

    @Override
    public void notifyObserversDelay(Flight flight, int minutes) {
        this.notificationsManager.notifyObserversDelay(flight, minutes);
    }

    @Override
    public void notifyObserversCancellation(Flight flight) {
        this.notificationsManager.notifyObserversCancellation(flight);
    }

    @Override
    public void notifyObserversSale(Flight flight, int percentage) {
        this.notificationsManager.notifyObserversSale(flight, percentage);
    }

    public void printObservers() {
        this.notificationsManager.printObservers();
    }

    /**************** END OF FUNCTIONS DELEGATED TO NOTIFICATION MANAGER ****************
     **************** START OF FUNCTIONS DELEGATED TO WORKERS MANAGER ****************/
    public void assignWorkerToFlight(Flight flight, Worker worker) {
        this.workersManager.assignWorkerToFlight(flight, worker);
    }

    public void removeWorkerFromFlight(Flight flight, Worker worker) {
        this.workersManager.removeWorkerFromFlight(flight, worker);
    }

    public void addWorker(Worker worker) {
        this.workersManager.addWorker(worker);
    }

    public void removeWorker(Worker worker) {
        this.workersManager.removeWorker(worker);
    }

    public void printWorkers() {
        this.workersManager.printWorkers();
    }

    /**************** END OF FUNCTIONS DELEGATED TO WORKERS MANAGER ****************
     **************** FUNCTIONS THAT ARE NOT DELEGATED ****************/

    @Override
    public void printFlights() {
        int number = 0;
        System.out.println("Company name: " + name);
        for (Flight flight : flights) {
            number++;
            System.out.println(number + "." + flight);
        }
        for (Company company : subCompanies) {
            company.printFlights();
        }
    }


    public Company getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Company Name: ").append(this.name).append("\n");

        sb.append("Flights:\n");
        for (Flight flight : this.flights) {
            sb.append(flight.toString()).append("\n");
        }

        sb.append("Workers:\n");
        for (Worker worker : this.workers) {
            sb.append(worker.getName()).append(" - ").append(worker.getJobTitle()).append("\n");
        }

        return sb.toString();
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

}
