package controller;

import model.Database;
import view.events.ContactEvent;
import view.events.ListEvent;
import view.events.SearchEvent;

public class Controller {
	private static final String databaseFileName = "contacts.db";
	private Database db;

	public Controller() {

	}

	public void handleContactEvent(ContactEvent ce) {
		switch(ce.getContactEventType()) {
		case ADD: System.out.println("handling add event: " + ce.getName() + " with id # " + ce.getContactID());
		}
	}

	public void handleSearchEvent(SearchEvent se) {
		switch (se.getSearchType()) {
		case NAME: // handle name search
		case EMAIL: // handle email search
		}
	}

	public void handleListEvent(ListEvent le) {
		switch (le.getListEventType()) {
		case WRITE: // handle write
		case RESTORE: // handle restore
		case PRINT: // handle print
		}
	}
}
