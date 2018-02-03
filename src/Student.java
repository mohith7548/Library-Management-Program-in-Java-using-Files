import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Student implements Serializable {
	static boolean running = true;
	static int count = 0;
	public static List<Member> student = new ArrayList<Member>();
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] kune) {
		
		System.out.println("Welcome to the Library");
		while(running) {
			System.out.println("Enter your choice:\n0.Load Student database\n1.New user ?? Register yourself\n2.My Membership Account\n3.Issue Book\n4.List your Fellow Students\n5.save and exit Changes\n6.Return Book\n");
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
				increaseBookIssued();
				break;
				
			case 4:
				displayStudents();
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
		Librarian.returnBookbyStudent();
	}

	private static void displayStudents() {
		Iterator<Member> it = student.iterator();
		
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
			fos = new FileOutputStream("student_data");
			out = new ObjectOutputStream(fos);
			out.writeObject(student);
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
		
		File file = new File("student_data");
		
		if(file.exists()) {
			try {
				fis = new FileInputStream("student_data");
				in = new ObjectInputStream(fis);
				student = (List<Member>) in.readObject();
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

	private static void increaseBookIssued() {
		Librarian.issueBookToStudent();
	}

	public static void viewMembershipStatus() {
		Iterator<Member> it = student.iterator();
		System.out.println("Enter your memberID: ");
		String memberId = in.nextLine();
		memberId = in.nextLine();
		while(it.hasNext()) {
			Member m = (Member) it.next();
			if(m.memberId.equals(memberId)) {
				System.out.println(m.toString());
				return;
			}
		}
		System.out.print("Member donot exist!!");
	}

	private static void addMyself() {
		String memberId, type, dateOfMembership;
		int maxBookLimit;
		String name,address;
		long phoneno;
		
		memberId = "STUDENT-"+(++count);
		type = "student";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateOfMembership = dateFormat.format(date);
		maxBookLimit = 4;
		System.out.print("Enter Name: ");
		name = in.nextLine();
		name = in.nextLine();
		System.out.print("Enter address: ");
		address = in.nextLine();
		System.out.print("Enter phoneno: ");
		phoneno = in.nextLong();
		Member m = new Member(memberId,type,dateOfMembership,name,address,phoneno);
		student.add(m);
		System.out.println("Your MemberID: "+ memberId);
	}
	

}
