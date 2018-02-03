import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Book implements Serializable {

	public String bookId, author, name;
	private double price;
	private int rackNo;
	private boolean status;
	private int edition;
	private Date dateOfPurchase;
	protected String type;

	public Book(String bookId, String author, String name, double price, int edition) {

		this.bookId = bookId;
		this.author = author;
		this.name = name;
		this.price = price;
		Random randomNum = new Random();
		this.rackNo = 1 + randomNum.nextInt(10);
		this.status = true;
		this.edition = edition;
		this.dateOfPurchase =new Date();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String display = "Book Details are:"+"\nBookID: " + bookId + "\nAuthor: " + author + "\nBookName: " + name + "\nPrice: " + price
				+ "\nRackNo: " + rackNo + "\nStatus: " + status + "\nEdition: " + edition + "\nDate Of Purchase: "
				+ dateOfPurchase.toString();
		return display;
	}


	public void UpdateStatus(boolean status) {
		this.status = status;
	}

}
