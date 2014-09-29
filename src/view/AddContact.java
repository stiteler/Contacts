package view;

import java.util.Scanner;

import view.events.ContactEvent;
import view.events.ContactEventListener;
import view.events.ContactEvent.ContactEventType;

public class AddContact {
	private ContactEventListener cel;
	private ContactEvent contactEvent;
	private Scanner kb;
	
	public AddContact(ContactEventListener cel) {
		this.cel = cel;
		kb = new Scanner(System.in);
		contactEvent = new ContactEvent(this, ContactEventType.ADD);
		// prompt add contact information
		// create contactEvent
		// fireContactEvent
		
		// simple tests
		System.out.println("What is the name of the candidate you would like to add?");
		contactEvent.setName(kb.nextLine());
		System.out.println("You've added " + contactEvent.getName() + " to the list.");
		fireContactEvent();
	}
	
	private void fireContactEvent() {
		if(cel != null && contactEvent != null) {
			cel.contactEventOccurred(this.contactEvent);
		}
	}
}
