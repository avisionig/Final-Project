package launcher;

import databases.LibrarySubscriptionDB;
import models.employee.Librarian;
import models.student.Faculty;
import models.student.Student;
import models.student.StudentDegree;
import nonUserPackage.Book;

import java.io.*;
import java.time.LocalDate;

public class ProjectLauncher {
    public static void main(String[] args) throws IOException {
        Librarian librarian = new Librarian("123", "321", "qwe", "anton", "Pavel", 621, LocalDate.of(2005, 1, 1), LibrarySubscriptionDB.listLibSubscriptions);
        Book book = new Book("Demian","Sartre",LocalDate.of(2005, 1, 1));
        BufferedReader input = null;
        BufferedWriter output = null;

//        try {
//            input = new BufferedReader(new FileReader("/Users/adonis/JavaProjects/FinalProject/src/database.txt"));
//            output = new BufferedWriter(new FileWriter("/Users/adonis/JavaProjects/FinalProject/src/database2.txt"));
//            int c;
//            while ((c = input.read()) != -1) {
//                output.write(c);
//            }
//        } finally {
//            if (input != null) {
//                input.close();
//            }
//            if (output != null) {
//                output.close();
//            }
//        }

        librarian.addBook(book);

        librarian.showBooks();

        librarian.showSubscriptions();

    }
}
