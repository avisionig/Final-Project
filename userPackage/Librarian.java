package userPackage;

import nonUserPackage.Book;

import nonUserPackage.LibrarySubscription;
import uniSystemPackage.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.stream.Collectors;
/**
 * 
 * Singleton class of Librarian, adds books in database, views books, deletes and views subscriptions.
 *
 */
public class Librarian extends User {
	
	private static final long serialVersionUID = -8023422525795240858L;
	
	
	private static Librarian librarian = new Librarian("Super", "Librarian");
	private Librarian(String firstName, String lastName) {
		super(firstName, lastName);
		this.setUserID();
		// TODO Auto-generated constructor stub
	}
	
	public static Librarian getLibrarian() {
		return librarian;
	}

    public Vector<LibrarySubscription> getSubscriptions() {
        return Database.accessDB().getSubscriptions();
    }

    /**
     * That method invokes in Student class, when student takes book
     * @param student
     * @param book
     * @see userPackage.Student#getBook()
     */
    public void giveBook(Student student, Book book) {
    	LibrarySubscription ls =  findLibrarySubscriptionByStudentId(student.getUserID());
        if (ls != null) {
        	ls.addBook(book);
        }
    }
    /**
     * adds new book in database.
     */
    public void createBook() {
    	System.out.println("Write book name, author:");
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	String bookInfo;
		try {
			bookInfo = in.readLine();
			StringTokenizer st = new StringTokenizer(bookInfo);
			System.out.println("Write books content:");
			String bookContent = in.readLine();
			addBookInDatabase(new Book(st.nextToken(), st.nextToken(), LocalDate.now(), bookContent));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void addBookInDatabase(Book book) {
        Database.accessDB().getBooks().add(book);
    }

    //--
    public void showBooks() {
        Database.accessDB().getBooks().forEach(System.out::println);
    }

    
    public void showSubscriptions() {
    	Database.accessDB().getSubscriptions().forEach(System.out::println);
    }

    /**
     * creates subscription for student, that method invokes in student class when getSubscription() method is active.
     * @param student
     * @see userPackage.Student#getSubscription()
     */
    public void addLibrarySubscription(Student student) {
        String id = String.valueOf((int) (Math.random() * 1000 + 100));
        if (findLibrarySubscriptionById(id) != null) {
        	System.out.println("Subscription with this ID exists");
            return;
        }
        Database.accessDB().getSubscriptions().add(new LibrarySubscription(id, student));
    }

    
    public LibrarySubscription findLibrarySubscriptionByStudentId(String studentId) {
        List<LibrarySubscription> collect = Database.accessDB().getSubscriptions().stream().filter(s -> Objects.equals(s.getStudent().getUserID(), studentId)).collect(Collectors.toList());
        if (collect.size() != 0) {
            return collect.get(0);
        }
        return null;
    }
    
    public LibrarySubscription findLibrarySubscriptionById(String subscriptionId) {
        List<LibrarySubscription> collect = Database.accessDB().getSubscriptions().stream().filter(s -> Objects.equals(subscriptionId, s.getSubscriptionId())).collect(Collectors.toList());
        if (collect.size() == 0) {
            return null;
        }
        return collect.get(0);
    }
    
    
    public void removeLibrarySubscription(String subscriptionId) {
        LibrarySubscription librarySubscription = findLibrarySubscriptionById(subscriptionId);
        Database.accessDB().getSubscriptions().remove(librarySubscription);
    }


    protected void setUserID(){
        this.userID = "LIBRARIAN777";
    }
    /**
     * user menu for Librarian, all important methods that need to be invoked in UniSystem.
     * @see uniSystemPackage.UniSystem
     */
    public void userMenu(BufferedReader in) {
    	while(true) {
    		super.userMenu(in);
    		System.out.println("1.Add book\n2.View books\n3.View subscriptions\n4.Delete subscription");
    		String action;
			try {
				action = in.readLine();
				if(action.equals("Q")) {
					return;
				}
				int ac = Integer.parseInt(action);
				if(ac == 1) {
					this.createBook();
				}
				else if(ac == 2) {
					this.showBooks();
				}
				else if(ac == 3) {
					this.showSubscriptions();
				}
				else if(ac == 4) {
					this.showSubscriptions();
					String subID = in.readLine();
					this.removeLibrarySubscription(subID);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }

}