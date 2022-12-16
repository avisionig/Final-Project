package databases;

import models.Book;
import models.student.Faculty;
import models.student.Student;
import models.student.StudentDegree;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentsBD {
    public static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student("0", "query", "query123","Anton","Pavlovich", Faculty.FIT, LocalDate.of(2005, 1, 1),42400, StudentDegree.PhD));
    }
}
