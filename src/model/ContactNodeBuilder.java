package model;

import java.io.Serializable;

public class ContactNodeBuilder implements Serializable{
	private static int iDGenerator;
	
	public ContactNodeBuilder() {
		iDGenerator = 1000;
	}
	
	protected ContactNode buildNewNode(String name, String phone, String email) {
		ContactNode newNode = new ContactNode();
		newNode.setName(name);
		newNode.setPhone(phone);
		newNode.setEmail(email);
		newNode.setContactID(iDGenerator++);
		
		return newNode;
	}
}
