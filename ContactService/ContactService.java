// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/17/2025
// Module 3 Milestone

package contactApp;

import java.util.Random;
import java.util.Vector;

public class ContactService {
	
	// vector for sorting contacts
	private Vector<Contact> myContacts = new Vector<Contact>();
	
	// int counter for storing the number of contacts
	private int numContacts = 0;
	
	// getter for numContacts
	public int getNumContacts() {
		return numContacts;
	}
	
	// getter for contactList
	public Vector<Contact> getContactList() {
		return myContacts;
	}
	
	// function for adding contacts
	public void addContact(String contactID, String firstName, String lastName, String phoneNumber, String contactAddress) {
		
		// creates a new contact
		Contact newContact = new Contact(contactID, firstName, lastName, phoneNumber, contactAddress);
		
		// adds newContact to contactList
		myContacts.add(newContact);
		
		// increments contacts
		numContacts++;
	}
	
	public void addContact(Contact contact) {
		// adds Contact to contactList
		myContacts.add(contact);
		
		// increments
		numContacts++;
	}
	
	// removes contacts from the list
	public void removeContact(String contactID) {
		
		if (contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Contact ID invalid");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No contacts found");
		}
		
		int index = -1;
		for (Contact c: myContacts) {
			if (c.getContactID().equals(contactID)) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		
		else {
			myContacts.remove(index);
			numContacts--;
			System.out.println("Contact removed");
		}
	}
	
	public void removeContact(Contact contact) {
		myContacts.remove(contact);
		numContacts --;
	}
	
	// updates the contact
	public void updateContact(String ID, String update, int selection) {
		if (ID == null || ID.length() > 10 || update == null || selection < 0) {
			throw new IllegalArgumentException("Contact ID invalid");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No contacts found");
		}
		
		int index = -1;
		
		for (Contact c: myContacts) {
			if (c.getContactID().equals(ID)) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		
		Contact updatedContact = myContacts.get(index);
		
		switch(selection) {
			case 1: {
				updatedContact.setFirstName(update);
				break;
			}
		
			case 2: {
				updatedContact.setLastName(update);
				break;
			}
		
			case 3: {
				updatedContact.setPhoneNumber(update);
				break;
			}
		
			case 4: {
				updatedContact.setContactAddress(update);
				break;
			}
		
			default: {
			removeContact(myContacts.elementAt(index));
			addContact(updatedContact);
			}
		}
	}
	
	public void updatedContact(String ID, String firstName, String lastName, String phoneNumber, String contactAddress) {
		if (ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Contact ID invalid");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No contacts found");
		}
		
		int index = -1;
		
		for (Contact c: myContacts) {
			if (c.getContactID().equals(ID)) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		
		Contact tempContact = myContacts.get(index);
		
		tempContact.setFirstName(firstName);
		tempContact.setLastName(lastName);
		tempContact.setPhoneNumber(phoneNumber);
		tempContact.setContactAddress(contactAddress);
		
		myContacts.remove(index);
		myContacts.add(tempContact);
	}
	
	public String generateUniqueId() {
		Random rand = new Random();
		int newID = rand.nextInt(1000000000);
		String uniqueID = Integer.toString(newID);
		
		for (Contact c: myContacts) {
			while(c.getContactID().equals(uniqueID)) {
				newID = rand.nextInt(1000000000);
				uniqueID = Integer.toString(newID);
			}
		}
		
		System.out.println("New Contact ID created: " + uniqueID);
		return uniqueID;
	}
}
