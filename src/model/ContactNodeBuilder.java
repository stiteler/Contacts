package model;

import java.io.Serializable;

/*
 * ContactNodeBuilder creates ContactsNodes (builder pattern) ensuring
 * each has a unique contact id for identifcation later (for deletes, at the moment).
 */
public class ContactNodeBuilder implements Serializable {
	private static int iDGenerator;
	/*
	 *	I learned a lesson here: While this static int iDGenerator variable is great for
	 * assigning IDs while the app is running, static data isn't serialized because it doesn't
	 * belong to the specific ContactNodeBuilder object that's being saved I had to add a few
	 * lines of code and a separate variable to save the state, and then every time we build
	 * a new node we have to ensure the static variable is updated to the saved variable.  But
	 * then the question is, what's the point of the static int to begin with? 
	 */
	private Integer savedIDGenerator;

	public ContactNodeBuilder() {
		iDGenerator = 1000;
	}

	// creates a new ContactNode with name, phone, and email strings, assigns
	// the node an ID, and then returns it.
	protected ContactNode buildNewNode(String name, String phone, String email) {
		ContactNode newNode = new ContactNode();
		
		// always want to set the static var to saved state when creating new node:
		// if the savedIDGenerator is null, skip
		if(savedIDGenerator != null) {
			iDGenerator = savedIDGenerator;
		}
		
		newNode.setName(name);
		newNode.setPhone(phone);
		newNode.setEmail(email);
		newNode.setContactID(iDGenerator++);
		
		savedIDGenerator = iDGenerator;
		
		return newNode;
	}
}
