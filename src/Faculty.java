import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Faculty implements Serializable {
	static boolean running = true;
	static int count = 0;
	public static List<Member> faculty = new ArrayList<Member>();
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] kune) {
		
		System.out.println("Welcome to the Library");
		while(running) {
			System.out.println("Enter your choice:\n0.Load Faculty database\n1.New user ?? Register yourself\n2.My Membership Account\n3.Issue Book\n4.List your Fellow Facultians\n5.save and exit Changes\n6.Return a Book\n");
			int choice = in.nextInt();
			
			switch(choice) {
			case 0:
				//loadScript();
				break;
			case 1:
				addMyself();
				break;
				
			case 2:
				viewMembershipStatus();
				break;
				
			case 3:
				issueBook();
				break;
				
			case 4:
				displayFaculty();
				break;
				
			case 5:
				//saveAndExit();
				//System.exit(0);
				return;
				
			case 6:
				decreaseBookIssued();
				break;
			}
		}
	
	}

	private static void decreaseBookIssued() {
		Librarian.returnBookbyFaculty();
	}

	private static void displayFaculty() {
		Iterator<Member> it = faculty.iterator();
		
		while(it.hasNext()) {
			Member m = (Member) it.next();
			System.out.println(m.toString());
		}
		
	}

	private static void saveAndExit() {
		running = false;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream("faculty_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(faculty);
			out.close();		 	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void loadScript() {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		File file = new File("faculty_data");
		
		if(file.exists()) {
			try {
				fis = new FileInputStream("faculty_data");
				in = new ObjectInputStream(fis);
				faculty = (List<Member>) in.readObject();
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

	private static void issueBook() {
		Librarian.issueBookToFaculty();
	}

	public static void viewMembershipStatus() {
		Iterator<Member> it = faculty.iterator();
		System.out.println("Enter your memberID: ");
		String memberId = in.nextLine();
		memberId = in.nextLine();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
				System.out.println(m.toString());
				break;
			}
		}
	}

	private static void addMyself() {
		String memberId, type, dateOfMembership;
		String name,address;
		long phoneno;
		memberId = "FACUTY-"+(++count);
		type = "faculty";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateOfMembership = dateFormat.format(date);
		System.out.print("Enter Name: ");
		name = in.nextLine();
		name = in.nextLine();
		System.out.print("Enter address: ");
		address = in.nextLine();
		System.out.print("Enter phoneno: ");
		phoneno = in.nextLong();
		Member m = new Member(memberId,type,dateOfMembership,name,address,phoneno);
		faculty.add(m);
		System.out.println("Your MemberID: "+ memberId);
	}
}
