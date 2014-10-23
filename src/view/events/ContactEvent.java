package view.events;

import java.util.EventObject;

/*
 * ContactEvent is a class that encases the information required
 * to handle events dealing with contacts in our application.
 * At the moment, this event is used to handle adds/deletes (events 
 * for a single record)
 */
public class ContactEvent extends EventObject {
	private ContactEventType contactEventType;
	private int contactID;
	private String name;
	private String phone;
	private String email;

	// simple constructor
	public ContactEvent(Object source, ContactEventType contactEventType) {
		super(source);
		this.contactEventType = contactEventType;
	}

	// more detailed constructor for instantiating with contactID
	public ContactEvent(Object source, ContactEventType contactEventType,
			int contactID) {
		super(source);
		this.contactEventType = contactEventType;
		this.contactID = contactID;
	}

	// type of the contact event (easily extensible)
	public enum ContactEventType {
		ADD, DELETE,
	}

	// // setters/getters:
	public ContactEventType getContactEventType() {
		return contactEventType;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
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
}
