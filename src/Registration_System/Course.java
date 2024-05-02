package Registration_System;

import java.util.*;

public class Course implements SubjectCourse {
    private final int maxSeats;
    private int availableSeats;
    private final int courseCode;
    private final TypeOfCourse type;
    private final String courseName;
    private Lecturer lecturer;
    private PracticalTeacher practicalTeacher;
    private final Set<ObserverStudent> waitList = new HashSet<>();
    private static final Map<Integer, Course> courses = new HashMap<>();


    private Course(int maxSeats, int courseCode,TypeOfCourse type, String courseName) {
        this.maxSeats = maxSeats;
        this.availableSeats = maxSeats;
        this.courseCode = courseCode;
        this.type = type;
        this.courseName = courseName;
        this.lecturer = null;
        this.practicalTeacher = null;
    }

    public static Course getCourse(int maxSeats, int courseCode, TypeOfCourse type, String courseName) {
        if (courses.containsKey(courseCode)) {
            return courses.get(courseCode);
        }
        Course newCourse = new Course(maxSeats, courseCode, type, courseName);
        courses.put(courseCode, newCourse);
        return newCourse;
    }


    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public TypeOfCourse getType() {
        return type;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public PracticalTeacher getPracticalTeacher() {
        return practicalTeacher;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setPracticalTeacher(PracticalTeacher practicalTeacher) {
        this.practicalTeacher = practicalTeacher;
    }

    public void decreaseAvailableSeats() {
        this.availableSeats--;
    }

    public void increaseAvailableSeats() {
        this.availableSeats++;
    }

    public boolean waitListContains(Student student) {
        return waitList.contains(student);
    }

    @Override
    public void addToWaitList(ObserverStudent student) {
        waitList.add(student);
    }

    // this method will be used when a student successfully registers to the course.
    @Override
    public void removeFromWaitList(ObserverStudent student) {
        waitList.remove(student);
    }

    // notify all students in the waitlist of the specific course.
    @Override
    public void notifyStudents() {
        for (ObserverStudent student : waitList) {
            student.update(this);
        }
    }

    // override equals and hashcode because we want to compare course objects based on course code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseCode == course.courseCode;
    }
    // we need to override hashCode because we override equals
    // if two objects are equal according to equals() method, then their hash code must be same
    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course Details:\n");
        sb.append("Course Name: ").append(courseName).append("\n");
        sb.append("Course Code: ").append(courseCode).append("\n");
        sb.append("Course Type: ").append(type).append("\n");
        sb.append("Available Seats: ").append(availableSeats).append("\n");
        if (lecturer != null) {
            sb.append("Lecturer: ").append(lecturer.getName()).append("\n");
        } else {
            sb.append("Lecturer: Not Assigned\n");
        }
        if (practicalTeacher != null) {
            sb.append("Practical Teacher: ").append(practicalTeacher.getName()).append("\n");
        } else {
            sb.append("Practical Teacher: Not Assigned\n");
        }
        return sb.toString();
    }

}
