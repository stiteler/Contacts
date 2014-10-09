package controller;

import model.Database;
import view.events.ContactEvent;
import view.events.ListEvent;
import view.events.SearchEvent;

public class Controller {
	private static final String databaseFileName = "contacts.db";
	private Database db;

	public Controller() {
		db = new Database();
	}

	public void handleContactEvent(ContactEvent ce) {
		switch(ce.getContactEventType()) {
		case ADD: addContact(ce);
			break;
		case DELETE: deleteContact(ce);
			break;
		}
	}

	public void handleSearchEvent(SearchEvent se) {
		switch (se.getSearchType()) {
		case NAME: nameSearch(se);
			break;
		case EMAIL: emailSearch(se);
			break;
		}
	}

	public void handleListEvent(ListEvent le) {
		switch (le.getListEventType()) {
		case WRITE: writeList();
			break;
		case RESTORE: restoreList();
			break;
		case PRINT: printList();
			break;
		}
	}
	
	// contact event handlers
	private void addContact(ContactEvent ce) {
		db.addEntry(ce.getName(), ce.getPhone(), ce.getEmail());
	}
	
	private void deleteContact(ContactEvent ce) {
		db.deleteEntryByID(ce.getContactID());
	}
	
	// search event handlers
	private void nameSearch(SearchEvent se) {
		// TODO: search
		Search nameSearch = new Search();
	}
	
	private void emailSearch(SearchEvent se) {
		Search emailSearch = new Search();
	}
	
	// list event handlers
	private void writeList() {
		db.saveDatabaseToFile();
	}
	
	private void restoreList() {
		db.loadDatabaseFromFile();
	}
	
	private void printList() {
		System.out.println(db.toString());
	}
}
