package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;

public class Database {
	private static final String fileName = "src/contacts.data";
	
	private ContactList list;
	private File file;
	
	public Database() {
		list = new ContactList();
		file = new File(fileName);
	}
	
	public void addEntry(String name, String phone, String email) {
		list.insert(name, phone, email);
	}
	
	public void restoreList() {
		
	}
	
	public void deleteEntryByID(int id) {
		list.deleteByID(id);
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
			System.out.println("Error restoring database");
		} catch(IOException ioe) {
			System.out.println("Error restoring database");
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Error restoring database");
		}
	}
	
	// used to print the list: 
	public String toString() {
		return list.toString();
	}
	
	public void saveDatabaseToFile() {
		System.out.println("saving in db");
		//TODO Possibly keep track of meta data about last save? number of contacts, date/time etc? 
			try {
				FileOutputStream fileOutStream = new FileOutputStream(file);
				ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
				objOutStream.writeObject(this.list);
				
				objOutStream.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error writing file.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error writing file.");
			}
	}
}
