package view.events;

import java.util.EventObject;

// adds/deletes (events for a single record)
public class ContactEvent extends EventObject {
	private ContactEventType contactEventType;
	private int contactID;
	private String name;
	private String phone;
	private String email;
	
	public ContactEvent(Object source, ContactEventType contactEventType) {
		super(source);
		this.contactEventType = contactEventType;
	}
	
	public ContactEvent(Object source, ContactEventType contactEventType, int contactID) {
		super(source);
		this.contactEventType = contactEventType;
		this.contactID = contactID;
	}
	
	public enum ContactEventType {
		ADD, DELETE, 
	}
	
	public ContactEventType getContactEventType() {
		return contactEventType;
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
