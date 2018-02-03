import java.util.Date;
import java.util.Random;

public class Transaction {
	
	private String transId, memberId, bookId;
	private Date dateOfIssue, dueDate;
	
	public Transaction(String memberId, String bookId, Date dateOfIssue, Date dueDate) {
		this.transId = getRandTransNum();
		this.memberId = memberId;
		this.bookId = bookId;
		this.dateOfIssue = dateOfIssue;
		this.dueDate = dueDate;
		
	}
	
	public void createTransaction() {
		System.out.println("Your Transaction ID is: " + transId);
		System.out.println("Kindly Pay the Bill...!!\n Taking you t the Bill Section");
		Bill bill = new Bill(dueDate, memberId);
		bill.billCreate();
		return;
	}
	
	public void deleteTransaction() {
		
	}
	
	public void retriveTranstion() {
		
	}
	
	
	public String getRandTransNum() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuiopasdfghjklzxcvbnm";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}

}
