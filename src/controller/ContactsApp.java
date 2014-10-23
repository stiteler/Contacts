package controller;

import view.ContactsCLI;

// Class ContactsApp application object the main method.
// it simply instantiates our app controller and command line interface
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
