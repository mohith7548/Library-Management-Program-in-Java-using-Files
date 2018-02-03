import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Member implements Serializable {
	String memberId, type, dateOfMembership;
	int noOfBooksIssued, maxBookLimit;
	String name,address;
	long phoneno;
	public List<String> books = new ArrayList<String>();
	public List<Date> dueDateList = new ArrayList<Date>();
	public List<Date> dateOfIssueList = new ArrayList<Date>();

	public Member(String memberId, String type, String dateOfMembership, String name, String address, long phoneno) {
		this.memberId = memberId;
		this.type = type;
		this.dateOfMembership = dateOfMembership;
		this.noOfBooksIssued = 0;
		//this.maxBookLimit = 4;
		this.name = name;
		this.address = address;
		this.phoneno = phoneno;
	}
	
	@Override
	public String toString() {
		String buffer =  "\nMemberId: "+memberId+"\nType: "+type+"\nDate of MemberShip: "+dateOfMembership+
				"\nNo.of Books Issued: "+noOfBooksIssued+"\nMax Book Limit: "+maxBookLimit+
				"\nName: "+name+"\nAddress: "+address+"\nPhone Number: "+phoneno+"\nBooks Taken:\n";
		Iterator<String> it = books.iterator();
		if(it.hasNext()) {
			buffer += (String)it.next();
			buffer += "\n";
		}else {
			buffer += "None\n";
		}
		return buffer;
		
	}
	
}
