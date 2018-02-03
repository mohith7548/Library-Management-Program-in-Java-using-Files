import java.util.Scanner;

public class GrandMain {
	
	public static Scanner in = new Scanner(System.in);
	public static boolean running = true;

	public static void main(String[] kune) {
		System.out.println("Welcom to Library");
		while(running) {
			System.out.println("0>Load Databases\n1>Librarian\n2>Student\n3>Faculty\n4>Exit\n");
			int choice = in.nextInt();
			switch(choice) {
			case 0:
				Librarian.LoadScript();
				Student.loadScript();
				Faculty.loadScript();
				break;
			case 1:
				Librarian.main(null);
				break;
				
			case 2:
				Student.main(null);
				break;
				
			case 3:
				Faculty.main(null);
				break;
				
			case 4:
				running = false;
				break;
				
			default:
				break;
			}
		}
	}
}
/*BookID: strh
Author: jdtj
BookName: aweew
Price: 54.0
RackNo: 1
Status: true
Edition: 1
Date Of Purchase: Fri Feb 02 21:35:21 IST 2018
Type: Magazine*/