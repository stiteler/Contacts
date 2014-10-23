package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Database our primary model class that holds our data structures.
 * It has a public API including methods required for use by the controller 
 * to move data to and from the view. It also handles serialization of the
 * ContactList. 
 */
public class Database {
	private static final String fileName = "src/contacts.data";

	private ContactList list;
	private File file;

	public Database() {
		list = new ContactList();
		file = new File(fileName);
	}

	// insert a contact into the list
	public void addEntry(String name, String phone, String email) {
		list.insert(name, phone, email);
	}
	
	// delete a contact given an int id
	public void deleteEntryByID(int id) {
		list.deleteByID(id);
	}

	// restore list from file
	public void restoreList() {
		this.list = loadDatabaseFromFile();
	}

	// helper method that loads the database on file (used for copy/restore)
	private ContactList loadDatabaseFromFile() {
		FileInputStream fileInStream;
		ObjectInputStream objectInStream;
		ContactList contactListFromFile = null;

		try {
			// instantiate streams:
			fileInStream = new FileInputStream(file);
			objectInStream = new ObjectInputStream(fileInStream);
			contactListFromFile = (ContactList) objectInStream.readObject();
			objectInStream.close();
		} catch (FileNotFoundException fnfe) {
			System.out
					.println("Database file not found (normal for initial run)");
		} catch (Exception e) {
			System.out.println("Error loading database");
		}

		// On first run of application, db is null, so return a new one.
		if (contactListFromFile == null) {
			return new ContactList();
		}
		return contactListFromFile;
	}

	// used to print the list:
	public String toString() {
		return list.toString();
	}

	// writes the changes in the current list to file by serializing said
	// ContactList object. 
	public void saveDatabaseToFile() {
		try {
			// open streams
			FileOutputStream fileOutStream = new FileOutputStream(file);
			ObjectOutputStream objOutStream = new ObjectOutputStream(
					fileOutStream);

			// write the object
			objOutStream.writeObject(this.list);

			// close streams
			objOutStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error writing file.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error writing file.");
		}
		System.out.println("Contacts saved.");
	}

	// search our list by name
	public void queryByName(String name) {
		list.printContactsMatchingName(name);
	}

	// search our list by email
	public void queryByEmail(String email) {
		list.printContactsMatchingEmail(email);
	}

	// helper to copy the list if necessary without passing a reference to the
	// active list.
	public ContactList copy() {
		// to clone efficiently, will just reserialize object file into a new
		// list to return
		return loadDatabaseFromFile();
	}
}
