package Flights_Management_System.CompanyManagement;

import Flights_Management_System.Company;

import java.util.ArrayList;

public class SubCompaniesManager {
    private final ArrayList<Company> subCompanies;
    private final Company myCompany;

    public SubCompaniesManager(Company myCompany, ArrayList<Company> subCompanies) {
        this.subCompanies = subCompanies;
        this.myCompany = myCompany;
    }

    public void addSubCompany(Company company) {
        subCompanies.add(company);
        company.setOwner(myCompany);
    }

    public void removeSubCompany(Company company) {
        company.setOwner(null);
        subCompanies.remove(company);
    }


    public String getSubCompaniesNames() {
        StringBuilder names = new StringBuilder();
        for (Company company : subCompanies) {
            if (!names.isEmpty()) {
                names.append(", ");
            }
            names.append(company.getName());
        }
        return names.toString();
    }


}