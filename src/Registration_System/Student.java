package Registration_System;

import Registration_System.Exceptions.IdAlreadyInUseException;
import java.util.HashSet;
import java.util.Set;

public class Student extends academicPersonnel implements ObserverStudent {
    private final Set<Course> registeredCourses = new HashSet<>();
    private NotificationStrategy notificationStrategy;

    private Student(String name, int studentID, int password) {
        super(name, studentID, password);
    }

    /**
     * Create a student with a unique student ID
     * @param name student name to be created
     * @param studentID student ID to be created
     * @return a student object if the student ID is unique, otherwise throws an exception.
     */
    public static Student createStudent(String name, int studentID,int password) throws IdAlreadyInUseException {
        if (academicPersonnel.getExistingIds().containsKey(studentID)) {
            throw new IdAlreadyInUseException("Student ID " + studentID + " is already in use.");
        } else {
            return new Student(name, studentID,password);
        }
    }

    public void registerCourse (Course course){
        registeredCourses.add(course);
    }

    public void dropCourse (Course course){
        registeredCourses.remove(course);
    }

    public Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void printRegisteredCourses(){
        System.out.println("Registered courses for student " + getName() + " are:");
        for (Course course : registeredCourses) {
            System.out.println(course.getCourseName());
        }
    }

    public void setNotificationPreference(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    @Override
    public void update(Course course) {
        notificationStrategy.notifyStudents(this,course);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %-10d %-10d", getClass().getSimpleName(), getName(), getPersonalID(), getPassword()));
        sb.append(" Registered Courses: ");
        for (Course course : registeredCourses) {
            sb.append(course.getCourseName()).append(", ");
        }
        return sb.toString();
    }

}
