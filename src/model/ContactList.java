package model;

import java.io.Serializable;

// linked list class
public class ContactList implements Serializable {
	ContactNode head;
	ContactNode tail;

	public ContactList() {
		head = tail = null;
	}

	// for now we'll rely on all fields present
	protected void insert(String name, String phone, String email) {
		ContactNode newNode = new ContactNode();
		newNode.setName(name);
		newNode.setEmail(email);
		newNode.setPhone(phone);

		// TODO: Continue this.
	}
	
}
