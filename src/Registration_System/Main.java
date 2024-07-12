package Registration_System;
import Registration_System.Exceptions.IdAlreadyInUseException;
import Registration_System.Exceptions.LoginException;
import Registration_System.CourseCreation.RegistrationSystem;


public class Main {
    static Student aharon;
    static Student hagai;
    static Student sapir;
    static Student nadav;
    static Student gidi;
    static Student yuval;
    static Student yair;
    static Student gal;
    static Student dor;
    static Student david;
    static Student roni;
    static Student omer;
    static Lecturer gabriel;
    static Lecturer amit;
    static PracticalTeacher shani;
    static PracticalTeacher lior;


    public static void main(String[] args) throws LoginException{
        // Create a registration system
        RegistrationSystem rs = RegistrationSystem.getInstance();

        System.out.println();
        System.out.println("Creating students and staff... ");
        try {
            aharon = Student.createStudent("Aharon", 30644,22325528);
            rs.logIn(30644,22325528);

            hagai = Student.createStudent("Hagai", 32684,58302963);
            rs.logIn(32684,58302963);

            sapir = Student.createStudent("Sapir", 33684,85308596);
            rs.logIn(33684,85308596);

            nadav = Student.createStudent("nadav", 31854,12895949);
            rs.logIn(31854,12895949);

            gidi = Student.createStudent("Gidi", 22454,86857784);
            rs.logIn(22454,86857784);

            yuval = Student.createStudent("Yuval", 32216,32525161);
            rs.logIn(32216,32525161);

            yair = Student.createStudent("Yair", 23447,71333281);
            rs.logIn(23447,71333281);

            gal = Student.createStudent("Gal", 81235,98989201);
            rs.logIn(81235,98989201);

            dor = Student.createStudent("Dor", 29340,56215678);
            rs.logIn(29340,56215678);

            david = Student.createStudent("David", 20343,10045121);
            rs.logIn(20343,10045121);

            roni = Student.createStudent("Roni", 22045,97973141);
            rs.logIn(22045,97973141);

            omer = Student.createStudent("Omer", 31553,91312101);
            rs.logIn(31553,91312101);

        } catch (IdAlreadyInUseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();



        Lecturer refael; // an example of a lecturer with the same ID as Amit. we want to see the program prints an exception.

        try {
            gabriel = Lecturer.createLecturer("Gabriel",20513, 91827364);
            rs.logIn(20513, 91827364);

            amit = Lecturer.createLecturer("Amit", 21614, 56752538);
            rs.logIn(21614, 56752538);

            shani = PracticalTeacher.createPracticalTeacher("Shani", 31456, 91050501);
            rs.logIn(31456, 91050501);

            lior = PracticalTeacher.createPracticalTeacher("Lior", 27476, 13658121);
            rs.logIn(27476, 13658121);

            refael = Lecturer.createLecturer("Refael", 21614, 31419481);
            rs.logIn(21614, 31419481);

        } catch (IdAlreadyInUseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();



        System.out.println("Printing all active users: ");
        rs.printActiveUsers();
        System.out.println();

        System.out.println();
        System.out.println("Declaring two courses: Linear Algebra and Object Oriented Programming.");
        System.out.println();
        assert gabriel != null;
        Course linearAlgebra = gabriel.declareCourse(4, 101, TypeOfCourse.MANDATORY, "Linear Algebra");
        System.out.println();
        Course oop = gabriel.declareCourse(5, 131, TypeOfCourse.MANDATORY, "Object Oriented Programming");
        System.out.println();
        System.out.println();
        rs.registerCourse(sapir,oop);
        rs.registerCourse(aharon,oop);
        rs.registerCourse(nadav,oop);
        rs.logOut(hagai);
        System.out.println();
        System.out.println("Registered some students to courses");
        System.out.println();

        rs.registerCourse(yuval,oop);
        rs.registerCourse(yuval,linearAlgebra);
        rs.registerCourse(yair,oop);
        rs.registerCourse(yair,linearAlgebra);
        rs.registerCourse(gidi,oop);
        rs.registerCourse(gidi,linearAlgebra);
        rs.registerCourse(nadav,oop);
        rs.registerCourse(gal,oop);
        rs.registerCourse(gal,linearAlgebra);
        rs.registerCourse(dor,oop);
        rs.registerCourse(david,linearAlgebra);
        rs.registerCourse(roni,linearAlgebra);
        rs.registerCourse(omer,linearAlgebra);
        rs.registerCourse(hagai,linearAlgebra); // should print "Student is not logged in".
        rs.dropCourse(yair,linearAlgebra);
        rs.dropCourse(sapir,oop);
        System.out.println();
        rs.logIn(32684,58302963);   // logging in hagai
        rs.registerCourse(hagai,oop);
        rs.printActiveUsers();
        System.out.println();
        System.out.println("Registered lecturer gabriel and practical teacher lior to the OOP course.");
        System.out.println();
        rs.staffRegisterCourse(gabriel,oop);
        rs.staffRegisterCourse(lior,oop);
        assert oop != null;
        System.out.println("Printing current OOP course staff:");

        Lecturer lecturer = oop.getLecturer();
        if (lecturer != null) {
            System.out.println(lecturer.getName());
        } else {
            System.out.println("No lecturer assigned for this course.");
        }

        PracticalTeacher practicalTeacher = oop.getPracticalTeacher();
        if (practicalTeacher != null) {
            System.out.println(practicalTeacher.getName());
        } else {
            System.out.println("No practical teacher assigned for this course.");
        }

        System.out.println("Dropping  Lecturer Gabriel from OOP course:");
        System.out.println();
        rs.staffDropCourse(gabriel,oop);
        if (oop.getLecturer()!=null)
            System.out.println(oop.getLecturer().getName()); // making sure it worked.
        System.out.println("Changing the lecturer of the OOP course to Amit and the practical teacher to Shani.");
        System.out.println();
        rs.staffRegisterCourse(amit,oop);
        rs.staffRegisterCourse(shani,oop);
        System.out.println("Printing current OOP new course staff:");
        System.out.println(oop.getLecturer().getName());
        System.out.println(oop.getPracticalTeacher().getName());

        System.out.println("signing gabriel and lior to the linear algebra course.");
        rs.staffRegisterCourse(gabriel,linearAlgebra);
        rs.staffRegisterCourse(lior,linearAlgebra);

        System.out.println("Printing the final status of the active users:");
        rs.printActiveUsers();
        System.out.println();
        System.out.println("***Printing the courses information***");
        System.out.println();
        System.out.println(oop);
        System.out.println(linearAlgebra);
    }
}
