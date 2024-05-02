package Registration_System.CourseCreation;
// a singleton registration system

import java.util.*;

import Registration_System.*;
import Registration_System.Exceptions.*;

public class RegistrationSystem {

    private final Map<Integer, Course> courses = new HashMap<>(); // map of courses with course code as key
    private final Set<academicPersonnel> activeUsers = new HashSet<>(100); // we want to limit the number of active users to 100
    private static RegistrationSystem instance = null; // singleton instance



    private RegistrationSystem() { // private constructor to prevent instantiation
    }

    public static RegistrationSystem getInstance() { // singleton instance getter
        if (instance == null) {
            instance = new RegistrationSystem();
        }
        return instance;
    }


    /**
     * Creates a course with the given parameters. If the course code is already in use, the method returns the existing course.
     * we made the access modifier package-private to make sure that only staff could create courses.
     * @param staff - the staff member who wants to create the course. we pass the staff member as a parameter to check if the staff is logged in.
     * @param maxSeats - maximum of available seats in the course.
     * @param courseCode - unique for each course.
     * @param type - the type of the course (ELECTIVE, MANDATORY, SEMINAR).
     * @param courseName - the name of the course.
     * @return - the created course.
     * @throws LoginException - might happen if the staff is not logged in.
     */
    Course createCourse(academicPersonnel staff, int maxSeats, int courseCode, TypeOfCourse type, String courseName) throws LoginException {
        if (!activeUsers.contains(staff)) {
            throw new LoginException("User is not logged in.");
        }
        Course course = Course.getCourse(maxSeats, courseCode, type, courseName);
        if (!courses.containsKey(courseCode)) {
            courses.put(courseCode, course);
            System.out.println("Course " + courseName + " has been created.");
        } else {
            System.out.println("Course with the same course code already exists.");
        }
        return course;
    }

//    /**
//     * This function implements the Factory de
//     * Creates a course with the given parameters. If the course code is already in use, the method returns the existing course.
//     * we made the access modifier package-private to make sure that only staff could create courses.
//     * @param staff - the staff member who wants to create the course.
//     * @param maxSeats - maximum of available seats in the course.
//     * @param courseCode - unique for each course.
//     * @param type - the type of the course (ELECTIVE, MANDATORY, SEMINAR).
//     * @param courseName - the name of the course.
//     * @return - the created course.
//     * @throws LoginException - might happen if the staff is not logged in.
//     */
//     Course createCourse(academicPersonnel staff, int maxSeats, int courseCode, TypeOfCourse type, String courseName) throws LoginException {
//        if (!activeUsers.contains(staff)) {
//            throw new LoginException("User is not logged in.");
//        }
//        for (Course course : courses) {
//            if (course.getCourseCode() == courseCode) {
//                System.out.println("Course with the same course code already exists.");
//                return course;
//            }
//        }
//        Course newCourse = new Course(maxSeats, courseCode,type, courseName);
//        courses.add(newCourse);
//        System.out.println("Course " + courseName + " has been created.");
//        return newCourse;
//    }

    /**
     * Logs in a user with the given personID and password. If the user does not exist, the method prints "User does not exist."
     * If the password is incorrect, the method prints "Incorrect password."
     * If the system is full, the method prints "System is full. Please try again later."
     * @param personID - the ID of the user
     * @param password - the password of the user
     */
    public void logIn(int personID, int password) {
        if (activeUsers.size() == 100) {
            System.out.println("System is full. Please try again later.");
            return;
        }
        try {
            academicPersonnel user = academicPersonnel.getPersonnel(personID);
            if (user.getPassword() != password) {
                System.out.println("Hey " + user.getName() + " the password you entered is incorrect, please try again.");
            } else {
                activeUsers.add(user);
            }
        } catch (NoSuchElementException e) {
            System.out.println("User does not exist.");
        }
    }


    /**
     * Logs out the user. If the user is not logged in, the method prints "User is not logged in."
     * @param user - the user to log out
     */
    public void logOut(academicPersonnel user){
        if(!activeUsers.contains(user)) {
            System.out.println("User is not logged in.");
            return;
        }
        activeUsers.remove(user);
    }

    /**
     * Prints the active users and its details in a table format.
     */
    public void printActiveUsers() {
        System.out.printf("%-20s %-10s %-10s %-10s %-20s%n", "Academic Status", "Name", "ID", "Password", "Registered Courses");
        System.out.printf("%-20s %-10s %-10s %-10s %-20s%n", "--------------- ", "---- ", "-- ", "-------- ", "----------------- ");

        for (academicPersonnel user : activeUsers) {
            System.out.println(user);
        }
    }

    /**
     * Registers a staff member for a course. If the staff member is not logged in, the method prints "Staff is not logged in."
     * the function checks if the course exists and if the staff is a lecturer or a practical teacher,
     * then it registers the staff for the course. (meaning it signs the Lecturer as the lecturer of the course and the PracticalTeacher as the practical teacher of the course)
     *
     * @param staff  - the staff member to register
     * @param course - the course to register for
     */
    public void staffRegisterCourse(UniStaff staff, Course course) {
        if (!activeUsers.contains(staff)) {
            System.out.println("Staff is not logged in.");
            return;
        }
        if (!courses.containsKey(course.getCourseCode())) {
            System.out.println("Course does not exist.");
            return;
        }
        if(staff instanceof Lecturer) {
            course.setLecturer((Lecturer) staff);
            staff.getRegisteredCourses().add(course);
        }
        if(staff instanceof PracticalTeacher) {
            course.setPracticalTeacher((PracticalTeacher) staff);
            staff.getRegisteredCourses().add(course);
        }
    }

    /**
     * Drops a course for a staff member. If the staff member is not logged in, the method prints "Staff is not logged in."
     * If the course does not exist, the method prints "Course does not exist."
     * If the staff member is a lecturer, the method sets the lecturer of the course to null and removes the course from the staff member's registered courses.
     * @param staff - the staff member to drop the course for
     * @param course - the course to drop
     */
    public void staffDropCourse(UniStaff staff, Course course) {
        if (!activeUsers.contains(staff)) {
            System.out.println("Staff is not logged in.");
            return;
        }
        if (!courses.containsKey(course.getCourseCode())) {
            System.out.println("Course does not exist.");
            return;
        }
        if(staff instanceof Lecturer) {
            course.setLecturer(null);
            staff.getRegisteredCourses().remove(course);
        }
        if(staff instanceof PracticalTeacher) {
            course.setPracticalTeacher(null);
            staff.getRegisteredCourses().remove(course);
        }
    }

    /**
     * Registers a student for a course. If the course is full, the student decides whether they want to be added to the waitlist.
     * If the student is already on the waitlist, and he successfully registers for the course, he is removed from the waitlist.
     * @param student the student to register
     * @param course the course to register for
     */
    public void registerCourse(Student student, Course course) {
        if (!activeUsers.contains(student)) {
            System.out.println("Student is not logged in.");
            return ;
        }
        if (!courses.containsKey(course.getCourseCode())) {
            System.out.println("Course does not exist.");
            return ;
        }
        if (courses.containsKey(course.getCourseCode()) && course.getAvailableSeats() < 1) {
            Scanner scanner = new Scanner(System.in);
            String response;
            do {
                System.out.println("Hey " + student.getName() + "! the course " + course.getCourseName() +  " is full. Would you like to receive a notification when a seat becomes available? (Y/N)");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter either 'Y/y' or 'N/n'.");
                }
            } while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N"));


            if (response.equalsIgnoreCase("Y")) {
                String notificationMethod;
                do {
                    System.out.println("How would you like to get the notification ? (Email/Phone)");
                    notificationMethod = scanner.nextLine();
                    if (!notificationMethod.equalsIgnoreCase("Email") && !notificationMethod.equalsIgnoreCase("Phone")) {
                        System.out.println("Invalid input. Please enter either 'Email' or 'Phone'.");
                    }
                } while (!notificationMethod.equalsIgnoreCase("Email") && !notificationMethod.equalsIgnoreCase("Phone"));

                if (notificationMethod.equalsIgnoreCase("Email")) {
                    student.setNotificationPreference(new EmailNotification());
                } else if (notificationMethod.equalsIgnoreCase("Phone")) {
                    student.setNotificationPreference(new PhoneNotification());
                }
                course.addToWaitList(student); // Add student to waitlist
            }
            return ; // Return as the student is not registered to the course
        }

        student.registerCourse(course); // Register student to the course
        course.decreaseAvailableSeats(); // Decrease the available seats in the course
        if (course.waitListContains(student)) { // If the student is on the waitlist, remove him from the waitlist
            course.removeFromWaitList(student);
        }
    }


    /**
     * when course is dropped by the student it removes the course from the student's registered courses,
     * increases the available seats in the course and notifies the students in the waitlist.
     * @param student the student to drop the course for
     * @param course the course to drop
     */
    public void dropCourse(Student student, Course course) {
        if (!activeUsers.contains(student)) {
            System.out.println("User is not logged in.");
            return;
        }

        if (!courses.containsKey(course.getCourseCode())) {
            System.out.println("Course does not exist.");
            return ;
        }
        student.dropCourse(course);
        course.increaseAvailableSeats();
        course.notifyStudents();
    }
}
