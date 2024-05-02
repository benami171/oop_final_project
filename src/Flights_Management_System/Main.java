
package Flights_Management_System;
import Flights_Management_System.SearchStrategies.*;


import java.util.Random;
import java.util.ArrayList;

public class Main {

    static String[] fromLocations = {"Tel Aviv", "New York", "London", "Paris", "Berlin", "Tokyo", "Moscow", "Los Angeles", "Sydney", "Rome", "San Francisco", "Madrid", "Barcelona", "Amsterdam", "Vienna"};
    static String[] toLocations = {"Paris", "Berlin", "Tokyo", "Moscow", "Los Angeles", "Sydney", "Rome", "Tel Aviv", "New York", "London", "Sao Paulo", "Buenos Aires", "Mexico City", "Toronto", "Vancouver"};
    static String[] depDates = {"13/01/2024 11:00", "14/02/2024 12:00", "15/03/2024 13:00", "16/04/2024 14:00", "17/05/2024 15:00", "18/06/2024 16:00", "19/07/2024 17:00", "20/08/2024 18:00", "21/09/2024 19:00", "22/10/2024 20:00", "11/03/2025 18:30", "17/07/2025 19:30","02/08/2025 23:00", "19/09/2025 20:30", "21/11/2025 21:30"};
    static int j=0;


    // a method to create a company with 5 flights helps us make the main clearer.
    private static Company createCompany(String name) {
        Company company = new Company(name);
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // creation of random flight ID
        for (int i = 1; i <= 5; i++ ,j++) {
            // Generate 3 random uppercase letters
            String letters = "" + alphabet.charAt(random.nextInt(alphabet.length()))
                    + alphabet.charAt(random.nextInt(alphabet.length()))
                    + alphabet.charAt(random.nextInt(alphabet.length()));
            // Generate 2 random numbers
            String numbers = "" + random.nextInt(10) + random.nextInt(10);
            // Generate another random uppercase letter
            String letter = "" + alphabet.charAt(random.nextInt(alphabet.length()));
            // Generate another random number
            String number = "" + random.nextInt(10);
            // Combine all parts to form the flight ID
            String flightId = letters + numbers + letter + number;
            company.newFlight(flightId, fromLocations[j], toLocations[j], depDates[j], 20*i *(j+7), 100);
        }
        return company;
    }

    public static void main(String[] args) {
        // Create airport instance
        AirPort airport = AirPort.getInstance("Airport");
        SearchStrategy byDate = new SearchByDate();
        SearchStrategy byDestination = new SearchByDest();
        SearchStrategy byPrice = new SearchByPrice();



        // Create 3 companies and add them to the airport
        Company el_al = createCompany("EL AL");
        Company arkia = createCompany("ARKIA");
        Company israir = createCompany("ISRAIR");
        airport.addCompany(el_al);
        airport.addCompany(arkia);
        airport.addCompany(israir);

        // Print all the flights in the airport currently
        System.out.println("Printing all flights in the airport: ");
        airport.printAllFlights();
        System.out.println();
        // Create some workers and add them to the Airport/company and flights.
        Worker alissa = new Worker("Alissa", "alissa@gmail.com",56789000,21432641,98813132,JobTitles.PILOT);
        Worker ben = new Worker("Ben", "ben@gmail.com",56789001,21432642,85693625,JobTitles.COPILOT);
        Worker carl = new Worker("Carl", "carl@gmail.com",56789002,21432643,49785612,JobTitles.FLIGHT_ATTENDANT);

        Worker denise = new Worker("Denise", "denise@gmail.com",56363500,2122641,97845812,JobTitles.PILOT);
        Worker demian = new Worker("Demian", "demian@gmail.com",56129001,2140042,33625145,JobTitles.COPILOT);
        Worker omar = new Worker("Omar", "omar@gmail.com",56719192,29412143,98659868,JobTitles.FLIGHT_ATTENDANT);

        Worker guy = new Worker("Guy", "guy@gmail.com",56789033,21433084,74757477,JobTitles.GROUND_CREW);
        Worker dana = new Worker("Dana", "dana@gmail.com",56789003,21432644,11425478,JobTitles.GROUND_CREW);

        System.out.println("Adding alissa, ben, carl to EL AL as workers");
        el_al.addWorker(alissa);
        el_al.addWorker(ben);
        el_al.addWorker(carl);
        System.out.println();

        System.out.println("Adding denise, demian, omar to ARKIA as workers");
        arkia.addWorker(denise);
        arkia.addWorker(demian);
        arkia.addWorker(omar);
        System.out.println();

        System.out.println("Adding guy, dana to the airport as workers");
        airport.addWorker(guy);
        airport.addWorker(dana);
        System.out.println();

        // Assign workers to flights of the same company
        System.out.println("Assigning el al workers to el al flights and arkia workers to arkia flights");
        for (Flight flight : el_al.getFlights()) {
            el_al.assignWorkerToFlight(flight, alissa);
            el_al.assignWorkerToFlight(flight, ben);
            el_al.assignWorkerToFlight(flight, carl);
        }

        for (Flight flight : arkia.getFlights()) {
            arkia.assignWorkerToFlight(flight, denise);
            arkia.assignWorkerToFlight(flight, demian);
            arkia.assignWorkerToFlight(flight, omar);
        }

        System.out.println();

        // Create 15 customers.
        String[] customerNames = {"Aharon", "Benny", "Chaim", "Maria", "Eli", "Daniela", "Gabi", "Or", "Ilan", "Jack", "Kobe", "Lior", "Lebron", "Nati", "Ori"};
        ArrayList<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int uniquePhone = 50271000 + i * 59 + 100;
            int uniqueID = 20839000 + i * 93 + 13;
            Customer customer = new Customer(customerNames[i], customerNames[i] + "@gmail.com", uniquePhone, uniqueID);
            customers.add(customer);
        }

        System.out.println("******Booking some flights for customers****** ");
        System.out.println();
        Random random = new Random();
        ArrayList<Flight> flights = airport.getFlights();
        for (int i = 0; i < flights.size() - 2; i=i+2) {
            Flight flight = flights.get(i);
            for (int j = 0; j < 2; j++) {
                int randomIndex = random.nextInt(customers.size());
                el_al.bookFlight(flight, customers.get(randomIndex));
            }
            // print the passengers of the flight
            System.out.println();
            System.out.println("The passengers that booked the flight with ID: " + flight.getFlightId());
            for (Customer customer : flight.getFlightPassengers()) {
                System.out.println(customer);
            }
            System.out.println();
        }

        System.out.println("Printing the observers list of each company");
        el_al.printObservers();
        arkia.printObservers();
        israir.printObservers();
        System.out.println();


        // Delay some flights and see that the observers are notified
        // if the flight was not booked by any customer, there would be no observer to notify
        for (int i = 0; i < airport.getFlights().size()-4; i=i+2) {
                airport.delayFlight(airport.getFlights().get(i), 30+i*10);
        }
        System.out.println("Printing all airport flights list after the delays:");
        airport.printAllFlights();


        //create a couple of sales
        System.out.println();
        System.out.println("Creating a couple of sales: ");
        System.out.println();
        el_al.newSale(el_al.getFlights().getFirst(), 5);
        israir.newSale(israir.getFlights().getFirst(), 10);
        israir.newSale(israir.getFlights().getLast(), 20);
        arkia.newSale(arkia.getFlights().getFirst(), 15);
        arkia.newSale(arkia.getFlights().get(1), 25);

        System.out.println();
        // Cancel three flights and see that the observers are notified, the relevant observers are the customers who booked the flights.
        Flight flightToCancel = airport.getFlights().getFirst();
        airport.cancelFlight(flightToCancel); // using airport method
        el_al.cancelFlight(el_al.getFlights().getLast()); // using company method
        arkia.cancelFlight(arkia.getFlights().getLast()); // using company method

        System.out.println();
        System.out.println("Printing the airport flights status to see that all the changes applied: ");
        //print all flights of the airport
        airport.printAllFlights();
        // Cancel booking for 2 customers from each flight
        for (Flight flight : airport.getFlights()) {
            int count = 0;
            for (Customer customer : flight.getFlightPassengers()) {
                if (count < 2) {
                    flight.getOwner().cancelBooking(flight, customer);
                    count++;
                } else {
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("After cancelling booking for 2 customers from each flight: ");
        airport.printAllFlights();

        // some usages for the workers manager
        System.out.println();


        el_al.printWorkers();
        arkia.printWorkers();
        System.out.println();
        System.out.println("Printing existing companies in the airport: ");
        airport.printAllCompanies();

        el_al.addSubCompany(arkia);
        el_al.addSubCompany(israir);
        System.out.println("Flights of EL AL only:");
        el_al.printCompanyFlights();
        System.out.println();
        System.out.println("Sub-companies of EL AL: " + el_al.getSubCompaniesNames());
        System.out.println();

        System.out.println("Flights of EL AL and its sub-companies:");
        el_al.printFlights();
        System.out.println();
        el_al.removeSubCompany(israir);
        System.out.println("Flights of EL AL after removing ISRAIR:");
        el_al.printFlights();
        System.out.println();

        System.out.println("*********Search part*********");
        System.out.println();
        System.out.println("Searching using Company search method: ");
        el_al.search();

        System.out.println();
        /**
         * i want to allow searching flights in the airport by date, location, and price.
         */
        System.out.println("Searching using Airport search method: ");
        System.out.println();
        System.out.println("Searching for flights in the airport by dates: 13/01/2024 11:00 - 17/07/2025 19:30 ");
        airport.searchFlights(byDate, "13/01/2024 11:00 - 17/07/2025 19:30");
        System.out.println("Searching for flights in the airport by destination: Tel Aviv");
        airport.searchFlights(byDestination, "Tel Aviv");
        System.out.println("Searching for flights in the airport by price: 100,900");
        airport.searchFlights(byPrice, "100,900");

    }


}

