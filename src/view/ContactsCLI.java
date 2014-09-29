package view;

import java.util.Scanner;

import view.events.ContactEvent;
import view.events.ContactEventListener;
import view.events.ListEvent;
import view.events.ListEventListener;
import view.events.SearchEvent;
import view.events.SearchEventListener;
import controller.Controller;

public class ContactsCLI implements ContactEventListener, ListEventListener,
		SearchEventListener {
	private static Controller controller;
	private boolean appRunning;
	private Scanner kb;

	public ContactsCLI(Controller controller) {
		this.controller = controller;
		this.appRunning = true;
		kb = new Scanner(System.in);

		while (appRunning) {
			displayMenu();
			String userInput = getUserInput();

			switch (userInput) {
			case "a":
				addMenuItemSelected();
				break;
			}
		}
	}

	private void displayMenu() {
		System.out.println("Please select an action:");
		System.out.println("'a' - Add a contact to the list");
	}

	private String getUserInput() {
		// for now - simple, will need to clean/check
		String input = kb.nextLine();
		return input;
	}

	// example from menu?
	private void addMenuItemSelected() {
		AddContact ac = new AddContact(this);
	}

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
