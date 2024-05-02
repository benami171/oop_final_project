package Registration_System;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class academicPersonnel {
    private final String name;
    private final int personalID;
    private final int password;
    private static final Map<Integer, academicPersonnel> EXISTING_IDS = new HashMap<>();


    public academicPersonnel(String name, int personalID,int password) {
        this.name = name;
        this.personalID = personalID;
        this.password = password;
        EXISTING_IDS.put(personalID, this);
    }

    public static Map<Integer, academicPersonnel> getExistingIds() {
        return EXISTING_IDS;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public int getPersonalID() {
        return personalID;
    }

    public static academicPersonnel getPersonnel(int personalID) throws NoSuchElementException {
        academicPersonnel personnel = EXISTING_IDS.get(personalID);
        if (personnel == null) {
            throw new NoSuchElementException("No academic personnel found with ID: " + personalID);
        }
        return personnel;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10s %-10d %-10d",getClass().getSimpleName(), name, personalID, password);
    }

}
