
public class Journals extends Book {
	
	public Journals(String bookId, String author, String name, double price, int edition) {
		super(bookId, author, name, price, edition);
		this.type = "Journal";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (super.toString() + "\nType: " + type);
	}

}
