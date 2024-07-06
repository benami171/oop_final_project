# OOP COURSE - FINAL PROJECT
## Introduction
This project is seperated to 2 parts:
1. **Part 1** - Implementing a Flight management system.
2. **Part 2** - Implementing a Course Registration system.

## Part 1 - Flight management system
### Classes
1. **Flight** - Represents a flight.
2. **Company** - Represents a company that operates flights.
3. **Airport** - Represents an airport that will manage all the flights and airlines that operate in it.
4. **Customer** - Represents a customer that can book a flight.
5. **Worker** - Represents a crew member that can work on a flight. each worker has a job title. (e.g. pilot, ground crew, etc.)
6. **FlightManager** - Represents a manager that can manage the flights and workers.
7. **NotificationManager** - Represents a manager that can send notifications to customers and workers.
8. **SearchManager** - Represents a manager that handle the search of flights for the company.
9. **SubCompanyManager** - Represents a manager that can manage the sub-companies of the Company.

### Design Patterns

1. **Singleton** - Used in the Airport class, as we want to have only one instance of the airport to work with.
2. **Observer** - Used in the NotificationManager class as the Notification manager implements Subject interface and the Customer and Worker classes implement the Observer interface.
3. **Strategy** - Used in the SearchManager class, based on the preferences of the user, the SearchManager will use the appropriate search strategy.
4. **Composite** - Used in the Company class, as the company can have sub-companies and we want to treat the sub-companies and the company as the same.
