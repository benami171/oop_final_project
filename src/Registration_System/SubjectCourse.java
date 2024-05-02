package Registration_System;

/**
 * the system will implement this interface, so it could notify the students about available seats for a course
 */
public interface SubjectCourse {
    void addToWaitList(ObserverStudent student);
    void removeFromWaitList(ObserverStudent student);
    void notifyStudents();
}
