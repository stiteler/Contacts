package view;

import java.util.Scanner;

import view.events.ContactEvent;
import view.events.ContactEvent.ContactEventType;
import view.events.ContactEventListener;
import view.events.ListEvent;
import view.events.ListEvent.ListEventType;
import view.events.ListEventListener;
import view.events.SearchEvent;
import view.events.SearchEventListener;
import controller.Controller;

public class ContactsCLI implements ContactEventListener, ListEventListener,
		SearchEventListener {
	
	private Controller controller;
	private boolean appIsRunning;
	// the Scanner kb represents user keyboard input
	private Scanner kb;

	public ContactsCLI(Controller controller) {
		this.controller = controller;
		this.appIsRunning = true;
		kb = new Scanner(System.in);
		
		// welcome the user:
		System.out.println("Welcome to Contacts.\n");

		while (appIsRunning) {
			displayMenu();
			
			// getUserInput() returns a cleaned, lowercase, single character string
			String userInput = getUserInput();

			switch (userInput) {
			case "a":
				addMenuItemSelected();
				break;
			case "p": 
				printMenuItemSelected();
				break;
			case "s": 
				searchNameMenuItemSelected();
				break;
			case "e": 
				searchEmailMenuItemSelected();
				break;
			case "d": 
				deleteMenuItemSelected();
				break;
			case "w": 
				writeMenuItemSelected();
				break;
			case "r": 
				restoreMenuItemSelected();
				break;
			}
		}
	}

	private void displayMenu() {
		System.out.println("Please select an action:");
		System.out.println("'a' - Add a contact to the list");
		System.out.println("'p' - Print the entire list");
		System.out.println("'s' - Search by Name");
		System.out.println("'e' - Search by Email");
		System.out.println("'d' - Delete an Entry");
		System.out.println("'w' - Write the database to file");
		System.out.println("'r' - Restore saved database");
	}

	private String getUserInput() {
		String input = "";
		do {
			input = kb.nextLine().toLowerCase();
		} while (!isValidInput(input));

		return input;
	}

	// to be valid: 1 character, matching a, p, s, e, d, w, or r
	private boolean isValidInput(String input) {
		if (input.length() != 1) {
			System.out.println("Input must only be one character.");
			displayMenu();
			return false;
		}

		// match is a string holding all possible values from user input
		String match = "apsedwr";
		if (!match.contains(input)) {
			System.out.println("Invalid input.");
			displayMenu();
			return false;
		}
		return true;
	}

	//// Menu Item Selection handlers:
	private void addMenuItemSelected() {
		//AddContact ac = new AddContact(this);
		ContactEvent contactEvent = new ContactEvent(this, ContactEventType.ADD);
		
		// prompt for user input TODO: clean input
		System.out.println("What is the NAME of the candidate you would like to add?");
		contactEvent.setName(kb.nextLine());
		System.out.println("What is the PHONE # of the candidate you would like to add?");
		contactEvent.setPhone(kb.nextLine());
		System.out.println("What is the EMAIL of the candidate you would like to add?");
		contactEvent.setEmail(kb.nextLine());
		
		this.contactEventOccurred(contactEvent);
	}
	
	private void deleteMenuItemSelected() {
		Integer contactID = null;
		do {
			System.out.println("What is the contact ID # you wish to delete?");
			contactID = kb.nextInt();
		} while(contactID == null);
		
		String response = null;
		Character firstChar = null;
		//TODO would be nice to show the candidate we're deleting here.
		System.out.println("Are you sure you want to delete ID# " + contactID + "? (y/n)");
		do{
			//reset keyboard
			kb.nextLine();
			response = kb.nextLine();
			
			firstChar = response.toLowerCase().charAt(0);
			if(firstChar != 'y' && firstChar != 'n') {
				System.out.println("Response must be (y/yes or n/no)");
				continue;
			}
		} while(response == null);
		
		if(firstChar == 'y') {
			ContactEvent ce = new ContactEvent(this, ContactEventType.DELETE);
			ce.setContactID(contactID);
			this.contactEventOccurred(ce);
		} else {
			System.out.println("Unable to delete contact #" + contactID);
		}
		
	}
	
	private void printMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.PRINT);
		this.listEventOccurred(le);
		
		// wait till user is ready for menu again
		System.out.println("Press <ENTER> to continue...");
		kb.nextLine();
	}
	
	private void writeMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.WRITE);
		this.listEventOccurred(le);
	}
	
	private void restoreMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.RESTORE);
		this.listEventOccurred(le);
	}
	
	private void searchNameMenuItemSelected() {
		
	}
	
	private void searchEmailMenuItemSelected() {
		
	}

	
	//// Event Listener Notifications
	public void searchEventOcurred(SearchEvent se) {
		controller.handleSearchEvent(se);
	}

	public void contactEventOccurred(ContactEvent ce) {
		controller.handleContactEvent(ce);
	}

	public void listEventOccurred(ListEvent le) {
		controller.handleListEvent(le);
	}
}
