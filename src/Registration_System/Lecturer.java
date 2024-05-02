package Registration_System;

import Registration_System.Exceptions.IdAlreadyInUseException;
import Registration_System.CourseCreation.UniStaff;

public class Lecturer extends UniStaff {

    private Lecturer(String name, int personalID, int password) {
        super(name, personalID, password);
    }

    public static Lecturer createLecturer(String name, int personalID, int password) throws IdAlreadyInUseException {
        if (academicPersonnel.getExistingIds().containsKey(personalID)) {
            throw new IdAlreadyInUseException("Lecturer ID " + personalID + " is already in use.");
        } else {
            Lecturer lecturer = new Lecturer(name, personalID, password);
            academicPersonnel.getExistingIds().put(personalID, lecturer);
            return lecturer;
        }
    }
}
