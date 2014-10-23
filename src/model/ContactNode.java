package model;

import java.io.Serializable;

/*
 * Contact node is a single element in our ContactList. It
 * contains data relating to a contact, and references to the
 * previous and next nodes.  Nodes are created only through the ContactNodeBuilder
 * object which ensures that each is given a unique contactID.
 */
public class ContactNode implements Serializable {

	private String name;
	private String phone;
	private String email;

	private ContactNode next;
	private ContactNode prev;

	// used to uniquely identify each contact, generated by ContactNodeBuilder
	private int contactID;

	public ContactNode() {
		next = prev = null;
	}

	public String toString() {
		return "ID:" + contactID + ", " + name + ", " + phone + ", " + email
				+ "\n";
	}

	// // Getters & Setters
	protected void setContactID(int contactID) {
		this.contactID = contactID;
	}

	protected int getContactID() {
		return contactID;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		this.phone = phone;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected ContactNode getNext() {
		return next;
	}

	protected void setNext(ContactNode next) {
		this.next = next;
	}

	protected ContactNode getPrev() {
		return prev;
	}

	protected void setPrev(ContactNode prev) {
		this.prev = prev;
	}
}
