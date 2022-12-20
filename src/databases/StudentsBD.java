package databases;

import models.student.Faculty;
import models.student.Student;
import models.student.StudentDegree;

import java.time.LocalDate;
import java.util.Vector;

public class StudentsBD {
    public static Vector<Student> students;

    static {
        students = new Vector<>();
        students.add(new Student("0", "query", "query123", "Anton", "Pavlovich", Faculty.FIT, LocalDate.of(2005, 1, 1), 42400, StudentDegree.PhD));
    }
}
