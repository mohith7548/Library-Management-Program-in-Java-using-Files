
public class StudyBooks extends Book {

	public StudyBooks(String bookId, String author, String name, double price, int edition) {
		super(bookId, author, name, price, edition);
		this.type = "Study Book";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (super.toString() + "\nType: " + type);
	}
}
