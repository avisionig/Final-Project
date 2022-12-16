package launcher;

import services.LibraryRegister;
import services.impl.LibraryRegisterImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProjectLauncher {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String studentId = reader.readLine();
        String bookId = reader.readLine();
        String subscriptionId = reader.readLine();
        LibraryRegister libraryRegister = new LibraryRegisterImpl();
        libraryRegister.showBooks();
        libraryRegister.showSubscriptions();
        System.out.println("By StudentId - " + libraryRegister.findLibrarySubscriptionByStudentId(studentId));
        System.out.println("By BookID - " + libraryRegister.findLibrarySubscriptionByBookId(bookId));
        System.out.println("By SubscriptionID - " + libraryRegister.findLibrarySubscriptionById(subscriptionId));
        System.out.println("Show lists of all subs - " + libraryRegister.findAllLibrarySubscriptions());
    }
}
