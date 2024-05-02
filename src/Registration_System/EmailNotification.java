package Registration_System;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void notifyStudents(Student student, Course course) {
        System.out.println("Student " + student.getName() + " got a new email: course " + course.getCourseName() + " has available seats.");
    }
}
