package Registration_System;

import Registration_System.Exceptions.IdAlreadyInUseException;
import Registration_System.CourseCreation.UniStaff;

public class PracticalTeacher extends UniStaff {

    private PracticalTeacher(String name, int personalID, int password) {
        super(name, personalID, password);
    }

    public static PracticalTeacher createPracticalTeacher(String name, int personalID, int password) throws IdAlreadyInUseException {
        if (academicPersonnel.getExistingIds().containsKey(personalID)) {
            throw new IdAlreadyInUseException("Practical Teacher ID " + personalID + " is already in use.");
        } else {
            PracticalTeacher practicalTeacher = new PracticalTeacher(name, personalID, password);
            academicPersonnel.getExistingIds().put(personalID, practicalTeacher);
            return practicalTeacher;
        }
    }
}
