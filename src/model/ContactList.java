package model;

import java.io.Serializable;

// linked list class
public class ContactList implements Serializable {
	private ContactNode head;
	private ContactNode tail;
	private ContactNodeBuilder builder;
	private int count;

	public ContactList() {
		head = tail = null;
		builder = new ContactNodeBuilder();
	}

	// insert is in alphabetical order by name
	protected void insert(String name, String phone, String email) {
		ContactNode newNode = builder.buildNewNode(name, phone, email);

		// if list is empty
		if (this.size() == 0) {
			head = tail = newNode;
			count++;
			return;
		}
		
		// if newNode belongs as the new head 
		if(name.compareTo(head.getName()) <= 0) {
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
	
	protected boolean deleteByID(int id) {
		// make sure list contains at least 1 node
		if(size() <= 0) {
			return false;
		}
		
		// iterate through list, if id matches, delete it
		ContactNode runner = head;
		while(runner != null) {
			if(runner.getContactID() == id) {
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
		if(node == null || size() == 0) {
			return false;
		}
		
		// corner case: last node
		if(size() == 1) {
			head = tail = null;
			count--;
			return true;
		}
		
		// corner case: head node delete
		if(node == head) {
			head = node.getNext();
			head.setPrev(null);
			count--;
			return true;
		}
		
		// corner case: tail node delete
		if(node == tail) {
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
	
	protected int getIDByName(String name) {
		
		return 0;
	}

	protected int size() {
		return count;
	}

	protected boolean isEmpty() {
		return count == 0;
	}

	public String toString() {
		if (size() == 0) {
			return "<Empty>";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		
		ContactNode runner = head;
		while(runner != null) {
			sb.append(runner);
			runner = runner.getNext();
		}
		
		return sb.toString();
	}
}
