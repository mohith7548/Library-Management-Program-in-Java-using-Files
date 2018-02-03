import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Librarian implements Serializable{
	
	public static List<Book> collection = new ArrayList<Book>();
	public static List<Journals> collection_Journals = new ArrayList<Journals>();
	public static List<StudyBooks> collection_StudyBooks = new ArrayList<StudyBooks>();
	public static List<Magazines> collection_Magazines= new ArrayList<Magazines>();

	public static final String name = "root" ;
	public static final String password = "toor" ;
	public static Scanner in = new Scanner(System.in);
	static boolean running = true;
	
	public static void main(String[] kune) {
		System.out.print("Enter Username: ");
		if( ! (in.next().equals(name)) ) {
			System.out.println("Wrong Username!!");
			System.exit(0);
		}
		System.out.print("Enter Password: ");
		if( ! (in.next().equals(password)) ) {
			System.out.println("Wrong Password!!");
			System.exit(0);
		}
		System.out.println("Welcome Librarian!!"+"\nWhat you want to do?\n");
		while(running) {
			System.out.println("\n0.Load Library\n1.Add a New Book\n2.Search a Book"+
					 "\n3.Verify a member\n4.Retrive Book\n5.Save and Exit\n6.Display Library");
			int choice = in.nextInt();
			
			switch(choice) {
			case 0:
				//LoadScript();
				break;
				
			case 1:
				addBook();
				break;
				
			case 2:
				boolean b;
				System.out.println("Enter a Book ID: ");
				String req_book_Id = in.nextLine();
				req_book_Id = in.nextLine();
				System.out.println("Enter type: ");
				int type = in.nextInt();
				if(type == 1) {
					b = searchBook_in_Journals(req_book_Id);
				} else if(type == 2) {
					b = searchBook_in_StudyBooks(req_book_Id);
				} else if(type == 3) {
					b = searchBook_in_Magazines(req_book_Id);
				} else {				
					b = searchBook(req_book_Id);
				}
				break;
			
			case 3:
				System.out.println("Press 1: if student\n2: if Faculty");
				int s = in.nextInt();
				if( s == 1)
					Student.viewMembershipStatus();
				if( s == 2)
					Faculty.viewMembershipStatus();
				break;
				
			case 4:
				System.out.println("Book is yet to be returned by student or Faculty!!");
				break;
				
			case 5:
				//saveAndExit();
				//break;
				return;
				
			case 6:
				displayLibrary();
				break;
				
			default:
				return;
			}
		}
		
		
	}

	private static boolean searchBook_in_Magazines(String req_book_Id) {
		Iterator<Magazines> it = collection_Magazines.iterator();
		while(it.hasNext()) {
			Magazines b = (Magazines)it.next();
			if(req_book_Id.equals(b.bookId)) {
				System.out.println("Book Found!!");
				System.out.println(b.toString());
				return true;
			}
		}
		return false;
	}

	private static boolean searchBook_in_StudyBooks(String req_book_Id) {
		Iterator<StudyBooks> it = collection_StudyBooks.iterator();
		while(it.hasNext()) {
			StudyBooks b = (StudyBooks)it.next();
			if(req_book_Id.equals(b.bookId)) {
				System.out.println("Book Found!!");
				System.out.println(b.toString());
				return true;
			}
		}
		return false;
	}

	private static boolean searchBook_in_Journals(String req_book_Id) {
		Iterator<Journals> it = collection_Journals.iterator();
		while(it.hasNext()) {
			Journals b = (Journals)it.next();
			if(req_book_Id.equals(b.bookId)) {
				System.out.println("Book Found!!");
				System.out.println(b.toString());
				return true;
			}
		}
		return false;
	}

	public static void issueBookToStudent() {
		System.out.println("Enter your member ID: ");
		String memberId = in.nextLine();
		Iterator<Member> it = Student.student.iterator();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
				if(m.maxBookLimit < 5) {
					System.out.println("Enter a Book ID: ");
					String req_book_Id = in.nextLine();
					if(searchBook(req_book_Id)) {
						System.out.println("BUG_01");
						m.books.add(req_book_Id);
						Date todaysDate = new Date();
						m.dateOfIssueList.add(todaysDate);
						//Adding due date to the book issued --- 5 days maxtime
						m.dueDateList.add(addDays(todaysDate, 5));
						return;
					}
				}
				else
					{System.out.println("\nYou already Crossed No.of Books Limit!!\n");return;}
			}
		}
	}
	
	public static void issueBookToFaculty() {
		System.out.println("Enter your member ID: ");
		String memberId = in.nextLine();
		Iterator<Member> it = Faculty.faculty.iterator();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
					System.out.println("Enter a Book Name: ");
					String req_book_name = in.nextLine();
					req_book_name = in.nextLine();
					if(searchBook(req_book_name)) {
						m.books.add(req_book_name);
						return;
					}
			}
		}
	}
	
	private static void displayLibrary() {
		Iterator<Book> it = collection.iterator();
		while(it.hasNext()) {
			Book b = (Book) it.next();
			System.out.println(b.toString());
		}
	}

	private static boolean searchBook(String req_book_Id) {
		Iterator<Book> it = collection.iterator();
		while(it.hasNext()) {
			Book b = (Book)it.next();
			if(req_book_Id.equals(b.bookId)) {
				System.out.println("Book Found!!");
				System.out.println(b.toString());
				return true;
			}
		}
		return false;
	}

	private static void addBook() {
		String bookId, author, name;
		double price;
		int edition;
		System.out.println("Enter BookID: " );
		bookId = in.next();
		System.out.println("Enter Author: " );
		author = in.nextLine();
		author = in.nextLine();
		System.out.println("Enter Book Name: " );
		name = in.nextLine();
		System.out.println("Enter Price: " );
		price = in.nextInt();
		System.out.println("Enter Edition: " );
		edition = in.nextInt();
		System.out.println("Enter Type: \n1.Journals\n2.StudyBook\n3.Magazine");
		int type = in.nextInt();
		if(type == 1) {
			Journals j = new Journals (bookId, author, name, price, edition);
			collection_Journals.add(j);
			collection.add(j);
		} else if(type == 2) {
			StudyBooks s = new StudyBooks (bookId, author, name, price, edition);
			collection_StudyBooks.add(s);
			collection.add(s);
		} else if(type == 3) {
			Magazines m = new Magazines (bookId, author, name, price, edition);
			collection_Magazines.add(m);
			collection.add(m);
		}
	}

	private static void saveAndExit() {
		running = false;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream("library_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(collection);
			out.close();
			fos = new FileOutputStream("Journals_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(collection_Journals);
			out.close();
			fos = new FileOutputStream("StudyBooks_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(collection_StudyBooks);
			out.close();
			fos = new FileOutputStream("Magazines_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(collection_Magazines);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void LoadScript() {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		File file = new File("library_data");
		if(file.exists()) {
			try {
				fis = new FileInputStream("library_data");
				in = new ObjectInputStream(fis);
				collection = (List<Book>) in.readObject();
				fis.close();
				in.close();
				fis = new FileInputStream("Journals_data");
				in = new ObjectInputStream(fis);
				collection_Journals = (List<Journals>) in.readObject();
				fis.close();
				in.close();
				fis = new FileInputStream("StudyBooks_data");
				in = new ObjectInputStream(fis);
				collection_StudyBooks = (List<StudyBooks>) in.readObject();
				fis.close();
				in.close();
				fis = new FileInputStream("Magazines_data");
				in = new ObjectInputStream(fis);
				collection_Magazines = (List<Magazines>) in.readObject();
				fis.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("\nThe File Doesn't Exists!!");
		}
	}

	public static void returnBookbyStudent() {
		System.out.println("Enter your member ID: ");
		String memberId = in.nextLine();
		Iterator<Member> it = Student.student.iterator();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
				if(m.maxBookLimit < 5) {
					System.out.println("Enter the Book ID to return: ");
					String req_book_Id = in.nextLine();
					req_book_Id = in.nextLine();
					if(searchBook(req_book_Id)) {
						m.books.remove(req_book_Id);
						Date dueDate = m.dueDateList.get(m.books.lastIndexOf(req_book_Id));
						Date todaysDate = new Date();
						Date dateOfIssue = m.dateOfIssueList.get(m.books.lastIndexOf(req_book_Id));
						if(dueDate.compareTo(todaysDate) < 0) {
							Transaction transaction = new Transaction(m.memberId, req_book_Id, dateOfIssue, dueDate);
							transaction.createTransaction();
						}
						return;
					}
				}
				else if(m.maxBookLimit == 0)
					{System.out.println("\nYou have no Books to return!!\n");return;}
			}
		}
	}

	public static void returnBookbyFaculty() {
		System.out.println("Enter your member ID: ");
		String memberId = in.nextLine();
		Iterator<Member> it = Faculty.faculty.iterator();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
				if(m.maxBookLimit != 0) {
					System.out.println("Enter the Book Id to return: ");
					String req_book_Id = in.nextLine();
					req_book_Id = in.nextLine();
					if(searchBook(req_book_Id)) {
						m.books.remove(req_book_Id);
						return;
					}
				}
				else
					{System.out.println("\nYou have no Books to return!!\n");return;}
			}
		}
		
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);		
		return cal.getTime();
	}
	
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);		
		return cal.getTime();
	}


}
