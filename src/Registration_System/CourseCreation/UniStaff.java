package Registration_System.CourseCreation;

import Registration_System.Course;
import Registration_System.Exceptions.LoginException;
import Registration_System.TypeOfCourse;
import Registration_System.academicPersonnel;

import java.util.HashSet;
import java.util.Set;

public abstract class UniStaff extends academicPersonnel {
    private final Set<Course> registeredCourses;


    public UniStaff(String name, int personalID,int password) {
        super(name, personalID, password);
        registeredCourses = new HashSet<>();
    }


    public Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public Course declareCourse(int maxSeats, int courseCode, TypeOfCourse type, String courseName) throws LoginException{
        return RegistrationSystem.getInstance().createCourse(this, maxSeats, courseCode, type, courseName);
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
