package Flights_Management_System;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserversDelay(Flight flight,int minutes);
    void notifyObserversCancellation(Flight flight);
    void notifyObserversSale(Flight flight,int percentage);
}

