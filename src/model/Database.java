package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Database {
	private static final String fileName = "src/contacts.data";
	
	private ContactList list;
	private File file;
	
	public Database() {
		list = new ContactList();
		file = new File(fileName);
	}
	
	public void addEntry() {
		
	}
	
	public void restoreList() {
		
	}
	
	public void deleteEntry() {
		
	}
	
	public void saveDatabaseToFile() {
		
	}
	
	public void loadDatabaseFromFile() {
		FileInputStream fileInStream;
		ObjectInputStream objectInStream;
		
		try {
			// instantiate streams:
			fileInStream = new FileInputStream(file);
			objectInStream = new ObjectInputStream(fileInStream);
			
			ContactList contactListFromFile = (ContactList) objectInStream.readObject();
			list = contactListFromFile;
			
			objectInStream.close();
		} catch(FileNotFoundException fnfe) {
			System.out.println("Could not find database file");
		} catch(IOException ioe) {
			System.out.println("Error loading file");
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Class not found when loading database");
		}
	}
	
}
