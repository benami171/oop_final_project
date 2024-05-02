package Registration_System;

public class PhoneNotification implements NotificationStrategy {
    @Override
    public void notifyStudents(Student student, Course course) {
        System.out.println("Student " + student.getName() + " got a new SMS: course " + course.getCourseName() + " has available seats.");
    }
}
