package controller;

import view.ContactsCLI;

public class ContactsApp {
	
	public static void main(String[] args) {
		ContactsApp app = new ContactsApp();
		app.run();
	}
	
	private void run() {
		Controller controller = new Controller();
		ContactsCLI cli = new ContactsCLI(controller);
	}
}
