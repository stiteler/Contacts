package view;

import java.util.Scanner;

import view.events.ContactEvent;
import view.events.ContactEvent.ContactEventType;
import view.events.ContactEventListener;
import view.events.ListEvent;
import view.events.ListEvent.ListEventType;
import view.events.SearchEvent.SearchType;
import view.events.ListEventListener;
import view.events.SearchEvent;
import view.events.SearchEventListener;
import controller.Controller;

/*
 * ContactsCLI is our main "view" class.  This class displays the menu to the user,
 * handles user input, and interfaces with the controller as per MVC design. ContactsCLI
 * also contains a app loop that continues until program exit. This class also listens to
 * application events and passes them up to the controller object.
 */
public class ContactsCLI implements ContactEventListener, ListEventListener,
		SearchEventListener {

	private Controller controller;
	private boolean appIsRunning;
	// the Scanner kb represents user keyboard input
	private Scanner kb;

	public ContactsCLI(Controller controller) {
		// instantiate data/objects
		this.controller = controller;
		this.appIsRunning = true;
		kb = new Scanner(System.in);

		// welcome the user:
		System.out.println("Welcome to Contacts.\n");

		// main application loop
		while (appIsRunning) {
			displayMenu();

			// getUserInput() returns a cleaned, lowercase, single character
			// string
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

	// displays the menu
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

	// gets menu user input
	private String getUserInput() {
		String input = "";
		do {
			input = kb.nextLine().toLowerCase();
		} while (!isValidInput(input));

		return input;
	}

	/*
	 * isValidInput checks user menu input for sanity to be valid: 1 character,
	 * matching a, p, s, e, d, w, or r
	 */
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

	// // Menu Item Selection handlers:
	// handles an add contact selection
	private void addMenuItemSelected() {
		ContactEvent contactEvent = new ContactEvent(this, ContactEventType.ADD);

		// prompt for user input TODO: clean input
		System.out
				.println("What is the NAME of the contact you would like to add?");
		contactEvent.setName(kb.nextLine());
		System.out
				.println("What is the PHONE # of the contact you would like to add?");
		contactEvent.setPhone(kb.nextLine());
		System.out
				.println("What is the EMAIL of the contact you would like to add?");
		contactEvent.setEmail(kb.nextLine());

		this.contactEventOccurred(contactEvent);
	}

	// handles a delete contact menu selection
	private void deleteMenuItemSelected() {
		Integer contactID = null;
		// get id to be deleted
		do {
			System.out.println("What is the contact ID # you wish to delete?");
			try {
				contactID = kb.nextInt();
			} catch (Exception e) {
				// in case the user puts in a non integer value
				System.out.println("contact ID must be an integer value");
				// clear System.in
				kb.nextLine();
			}
		} while (contactID == null);

		// ensure the user wants to delete the contact selected
		String response = null;
		Character firstChar = null;
		System.out.println("Are you sure you want to delete ID# " + contactID
				+ "? (y/n)");
		do {
			// reset keyboard
			kb.nextLine();
			response = kb.nextLine();

			firstChar = response.toLowerCase().charAt(0);
			if (firstChar != 'y' && firstChar != 'n') {
				System.out.println("Response must be (y/yes or n/no)");
				continue;
			}
		} while (response == null);

		// delete contact if user so wishes
		if (firstChar == 'y') {
			ContactEvent ce = new ContactEvent(this, ContactEventType.DELETE);
			ce.setContactID(contactID);
			this.contactEventOccurred(ce);
		} else {
			System.out.println("Ok, contact #" + contactID + " will not be deleted.");
		}

	}

	// handles print menu selection
	private void printMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.PRINT);
		this.listEventOccurred(le);

		// wait till user is ready for menu again
		System.out.println("Press <ENTER> to continue...");
		kb.nextLine();
	}

	// handles write menu item selection
	private void writeMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.WRITE);
		this.listEventOccurred(le);
	}

	// handles restore menu item selection
	private void restoreMenuItemSelected() {
		ListEvent le = new ListEvent(this, ListEventType.RESTORE);
		this.listEventOccurred(le);
	}

	// handles search by name menu item selection
	private void searchNameMenuItemSelected() {
		String query = null;
		do {
			System.out.println("What is the name you wish to search for?");
			query = kb.nextLine();
		} while (query == null);
		System.out.println("Searching...");

		SearchEvent se = new SearchEvent(this, query, SearchType.NAME);
		searchEventOcurred(se);
	}

	// handles search by email menu item selection
	private void searchEmailMenuItemSelected() {
		String query = null;
		do {
			System.out.println("What is the email you wish to search for?");
			query = kb.nextLine();
		} while (query == null);
		System.out.println("Searching...");

		SearchEvent se = new SearchEvent(this, query, SearchType.EMAIL);
		searchEventOcurred(se);
	}

	// // Event Listener Notifications
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
