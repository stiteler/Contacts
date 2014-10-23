package controller;

import model.Database;
import view.events.ContactEvent;
import view.events.ListEvent;
import view.events.SearchEvent;

/*
 * Controller is our main control class, it handles all the logic of the application,
 * primarily by interfacing between the view and the model (data).  It has methods that
 * handle events from the view and respond accordingly.
 */
public class Controller {
	private Database db;

	public Controller() {
		// create a new database object upon instantiation
		db = new Database();
		// expect error if first run of application (if file is not found)
		db.restoreList();
	}

	// handles contact events from the view
	public void handleContactEvent(ContactEvent ce) {
		switch (ce.getContactEventType()) {
		case ADD:
			addContact(ce);
			break;
		case DELETE:
			deleteContact(ce);
			break;
		}
	}

	// handles search events from the view
	public void handleSearchEvent(SearchEvent se) {
		switch (se.getSearchType()) {
		case NAME:
			nameSearch(se.getQuery());
			break;
		case EMAIL:
			emailSearch(se.getQuery());
			break;
		}
	}

	// handles list events from the view
	public void handleListEvent(ListEvent le) {
		switch (le.getListEventType()) {
		case WRITE:
			writeList();
			break;
		case RESTORE:
			restoreList();
			break;
		case PRINT:
			printList();
			break;
		}
	}

	// adds a contact
	private void addContact(ContactEvent ce) {
		db.addEntry(ce.getName(), ce.getPhone(), ce.getEmail());
	}

	// deletes a contact
	private void deleteContact(ContactEvent ce) {
		db.deleteEntryByID(ce.getContactID());
	}

	// searches by name
	private void nameSearch(String query) {
		db.queryByName(query);
	}

	// searches by email
	private void emailSearch(String query) {
		db.queryByEmail(query);
	}

	// list event handlers
	// write current list to file
	private void writeList() {
		db.saveDatabaseToFile();
	}

	// restore list from file
	private void restoreList() {
		db.restoreList();
	}

	// print list
	private void printList() {
		System.out.println(db.toString());
	}
}
