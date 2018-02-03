import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Bill {
	private String billno;
	private Date dueDate;
	private String memberId;
	private long amount;
	
	public static Scanner in = new Scanner (System.in);
	
	public Bill(Date dueDate, String memberId) {
		this.billno = getRandBillNum();
		this.dueDate = dueDate;
		this.memberId = memberId;
		this.amount = getDifferenceDays(dueDate, new Date());
	}
	
	public void billCreate() {
		System.out.println("Press 1 to pay the bill");
		int ch = in.nextInt();
		System.out.println("Thank You for paying Bill");
		billUpdate();
		return;
	}
	
	public void billUpdate() {
		System.out.println("Bill Updated Successfully");
	}
	
	public String getRandBillNum() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	public long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
}
