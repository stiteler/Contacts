package model;

public class ContactNode {
	private static Integer IDGenerator;
	
	private String name;
	private String phone;
	private String email;
	
	private ContactNode next;
	private ContactNode prev;
	
	// used to uniquely identify each contact
	private int contactID;
	
	public ContactNode() {
		if(IDGenerator != null) {
			IDGenerator = new Integer(1000);
		} else {
			this.contactID = IDGenerator++;
		}
	}

	public int getContactID() {
		return contactID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContactNode getNext() {
		return next;
	}

	public void setNext(ContactNode next) {
		this.next = next;
	}

	public ContactNode getPrev() {
		return prev;
	}

	public void setPrev(ContactNode prev) {
		this.prev = prev;
	}
}
