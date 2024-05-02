package Flights_Management_System;

import java.util.ArrayList;

public interface Component {
     void printFlights();
    String getSubCompaniesNames();
    void addSubCompany(Company company);
    void removeSubCompany(Company company);
}
