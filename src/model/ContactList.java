package model;

import java.io.Serializable;

/*
 * ContactList is our data structure in the model of our application.
 * It's a doubly linked list with methods required for the bookkeeping of
 * the list for our app.  It implements serializable in order to be saved to
 * a file.
 */
public class ContactList implements Serializable {
	private ContactNode head;
	private ContactNode tail;
	private ContactNodeBuilder builder;
	private int count;

	// constructor for the list instantiates our node builder
	// and ensures head and tail are null
	public ContactList() {
		head = tail = null;
		builder = new ContactNodeBuilder();
	}

	// insert places a new contact node in our list. Insert is in alphabetical
	// order by name
	protected void insert(String name, String phone, String email) {
		ContactNode newNode = builder.buildNewNode(name, phone, email);

		// if list is empty
		if (this.size() == 0) {
			head = tail = newNode;
			count++;
			return;
		}

		// if newNode belongs as the new head
		if (name.compareTo(head.getName()) <= 0) {
			// do head add
			head.setPrev(newNode);
			newNode.setNext(head);
			head = newNode;
			count++;
			return;
		}

		// else, insert further down the list
		ContactNode runner = head;
		while (runner != null) {

			// if we reach the end without insert, put as new tail
			if (runner == tail) {
				newNode.setPrev(tail);
				tail.setNext(newNode);
				tail = newNode;
				count++;
				return;
			}

			// else, new node belongs in the middle somewhere
			if (name.compareTo(runner.getName()) <= 0) {
				// if newNode belongs alphabetically right after runner
				// then insert after runner
				ContactNode newAfterNode = runner.getNext();
				runner.setNext(newNode);
				newNode.setPrev(runner);
				newNode.setNext(newAfterNode);
				count++;
				return;
			}
			// advance the runner
			runner = runner.getNext();
		}
	}

	// deleteByID receives an integer id number, and removes the unique node
	// matching that id. (Our nodes are guaranteed unique through the node
	// builder.
	protected boolean deleteByID(int id) {
		// make sure list contains at least 1 node
		if (size() <= 0) {
			return false;
		}

		// iterate through list, if id matches, delete it
		ContactNode runner = head;
		while (runner != null) {
			if (runner.getContactID() == id) {
				return deleteNode(runner);
			}
			runner = runner.getNext();
		}

		// else return false (id not matched)
		return false;
	}

	// helper utility for deleting nodes from list.
	private boolean deleteNode(ContactNode node) {
		// ensure node exists
		if (node == null || size() == 0) {
			return false;
		}

		// corner case: last node
		if (size() == 1) {
			head = tail = null;
			count--;
			return true;
		}

		// corner case: head node delete
		if (node == head) {
			head = node.getNext();
			head.setPrev(null);
			count--;
			return true;
		}

		// corner case: tail node delete
		if (node == tail) {
			tail = node.getPrev();
			tail.setNext(null);
			count--;
			return true;
		}

		// general case for doubly linked list:
		ContactNode previousNode = node.getPrev();
		ContactNode nextNode = node.getNext();
		previousNode.setNext(nextNode);
		nextNode.setPrev(previousNode);
		node.setPrev(null);
		node.setNext(null);
		count--;
		return true;
	}

	// printContactsMatchingName prints each contact if argument is a substring
	// of
	// each given node in our list.
	//
	// (Vic: I am having a code smell about these methods being in here..
	// ideally i'd rather store a "contact" object in each node in our list
	// and in order to search I could return an array of matching contacts,
	// rather than passing
	// nodes up to the db or controller level, which I wouldn't want to do. I
	// didn't
	// do this because the specification for the project was to have nodes that
	// have
	// the three text fields for name, phone and email.
	protected void printContactsMatchingName(String name) {
		ContactNode runner = head;
		while (runner != null) {
			if (runner.getName().contains(name)) {
				System.out.println(runner);
			}
			runner = runner.getNext();
		}
	}

	// printContactsMatchingEmail prints each contact if argument is a substring
	// of
	// each given node in our list.
	protected void printContactsMatchingEmail(String email) {
		ContactNode runner = head;
		while (runner != null) {
			if (runner.getEmail().contains(email)) {
				System.out.println(runner);
			}

			runner = runner.getNext();
		}
	}

	// returns size of list
	protected int size() {
		return count;
	}

	// returns true if list is empty, false otherwise
	protected boolean isEmpty() {
		return count == 0;
	}

	// toString method for displaying contact list
	public String toString() {
		if (size() == 0) {
			return "<Empty>";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("\n");

		ContactNode runner = head;
		while (runner != null) {
			sb.append(runner);
			runner = runner.getNext();
		}

		return sb.toString();
	}
}
