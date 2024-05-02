package Flights_Management_System.CompanyManagement;

import Flights_Management_System.Company;
import Flights_Management_System.Flight;
import Flights_Management_System.Worker;

import java.util.ArrayList;
import java.util.Objects;

public class WorkersManager {
    private final ArrayList<Worker> workers;
    private final Company myCompany;

    public WorkersManager(Company myCompany,ArrayList<Worker> workers) {
        this.workers = workers;
        this.myCompany = myCompany;
    }

    public void addWorker(Worker worker) {
        worker.setCompany(myCompany.getName());
        workers.add(worker);
    }

    public void assignWorkerToFlight(Flight flight, Worker worker) {
        if(Objects.equals(flight.getOwner().getName(), worker.getCompany())){
            flight.addCrewMember(worker);
        } else {
            throw new IllegalArgumentException("Worker is not from the same company as the flight");
        }
    }

    public void removeWorkerFromFlight(Flight flight, Worker worker) {
        if(Objects.equals(flight.getOwner().getName(), worker.getCompany())){
            flight.removeCrewMember(worker);
        }
        throw new IllegalArgumentException("Worker is not from the same company as the flight");
    }

    public void removeWorker(Worker worker) {
        if (Objects.equals(worker.getCompany(), myCompany.getName())) {
            workers.remove(worker);
        }
        throw new IllegalArgumentException("Worker is not from the same company as the manager");
    }

    public void printWorkers() {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}
