package Flights_Management_System;

public class Worker extends Person{

    private int workerId;
    private JobTitles jobTitle;
    private String Company;

    public Worker(String name, String email, int phone, int personalID,int workerId, JobTitles jobTitle) {
        super(name, email, phone, personalID);
        this.jobTitle = jobTitle;
        this.workerId = workerId;
    }

    public int getWorkerId() {
        return this.workerId;
    }

    public JobTitles getJobTitle() {
        return jobTitle;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getCompany() {
        return Company;
    }

    public void setJobTitle(JobTitles jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String toString() {
        return "Worker Name: " + this.getName() + "\n" +
                "Company: " + this.getCompany() + "\n" +
                "Job Title: " + this.getJobTitle() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Phone: " + this.getPhone() + "\n" +
                "Personal ID: " + this.getPersonalID() + "\n";

    }
}
