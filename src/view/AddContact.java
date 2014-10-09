package view;

import java.util.Scanner;

import view.events.ContactEvent;
import view.events.ContactEventListener;
import view.events.ContactEvent.ContactEventType;

//TODO delete this class
public class AddContact {
	private ContactEventListener cel;
	private ContactEvent contactEvent;
	private Scanner kb;
	
	public AddContact(ContactEventListener cel) {
		// instatiate objects
		this.cel = cel;
		kb = new Scanner(System.in);
		contactEvent = new ContactEvent(this, ContactEventType.ADD);

		// prompt for user input TODO: clean input
		System.out.println("What is the NAME of the candidate you would like to add?");
		contactEvent.setName(kb.nextLine());
		System.out.println("What is the PHONE # of the candidate you would like to add?");
		contactEvent.setPhone(kb.nextLine());
		System.out.println("What is the EMAIL of the candidate you would like to add?");
		contactEvent.setEmail(kb.nextLine());
		fireContactEvent();
	}
	
	private void fireContactEvent() {
		if(cel != null && contactEvent != null) {
			cel.contactEventOccurred(this.contactEvent);
		}
	}
}
