// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/17/2025
// Module 3 Milestone

package contactApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
	
	
	@Test
	void testAddContactMethod() {
		// creates contact
		ContactService contactService = new ContactService();
		String testID = contactService.generateUniqueId();
		Contact contact = new Contact(testID, "Adam", "Moore", "7146524431", "2265 Euclid St");
		
		// adds a contact to the list
		contactService.addContact(contact);
		
		// contact added confirmation
		assertTrue(!contactService.getContactList().isEmpty());
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getContactID()
				.equals(testID));
		assertTrue(contactService.getNumContacts() > 0);
	}

	@Test
	void testRemoveContactMethod() {
		ContactService contactService = new ContactService();
		// creates new contact
		Contact contact = new Contact("654321", "Adam", "Moore", "7146524431", "2265 Euclid St");
		
		// attempts to remove with null id
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("");
		});
		
		// attempts to remove with an id that is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("12345678901");
		});
		
		// attempts to remove from an empty list
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("1234567890");
		});
		
		// adds contact
		contactService.addContact(contact);
		
		// removes a contact that doesn't exist
		contactService.removeContact("754321");
		
		// contact list is not empty, count is not zero
		// contact not removed because contact doesn't exist
		assertTrue(!contactService.getContactList().isEmpty());
		assertTrue(contactService.getNumContacts() != 0);
		
		// removes correct contact
		contactService.removeContact("654321");
		
		// list is empty, count is zero, contact was removed
		assertTrue(contactService.getNumContacts() == 0);
		assertTrue(contactService.getContactList().isEmpty());
		
	}
	
	@Test
	void testUpdateContactMethodErrors() {
		ContactService contactService = new ContactService();
		// contact list is empty
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("654321", "Justin", 1);
		});
		
		// creates new contact, adds to list
		Contact contact = new Contact("654321", "Adam", "Moore", "7146524431", "2265 Euclid St");
		contactService.addContact(contact);
		// checks that contact was added
		assertTrue(!contactService.getContactList().isEmpty());
		
		// id is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("12345678901", "Justin", 1);
		});
		// id is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact(null, "Justin", 1);
		});
		// update value is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("654321", null, 1);
		});
		// selection value is less than zero
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("654321", "Justin", -1);
		});
		
		// prints "contact not found" to console
		contactService.updateContact("754321", "Justin", 1);
		
		// prints "contact not updated" to console
		contactService.updateContact("654321", "Justin", 5);
		
	}
	
	@Test
	void testUpdateContactMethod() {
		ContactService contactService = new ContactService();
		Contact contact = new Contact("654321", "Adam", "Moore", "7146524431", "2265 Euclid St");
		contactService.addContact(contact);
		assertTrue(!contactService.getContactList().isEmpty());
		
		// updates first name
		contactService.updateContact("654321", "Justin", 1);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getName()
				.equals("Justin Moore"));
		// updates last name
		contactService.updateContact("654321", "Smith", 2);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getName()
				.equals("Justin Smith"));
		// updates phone number
		contactService.updateContact("654321", "6574409900", 3);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getPhoneNumber()
				.equals("6574409900"));
		// updates address
		contactService.updateContact("654321", "1215 Harbor Blvd", 4);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getContactAddress()
				.equals("1215 Harbor Blvd"));
		
		// update fails because first name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("654321", "JustinSmith", 1);
		});
				
		// check that list has been updated
		// only one contact in list, check that it's updated by checking name
		assertTrue(contactService.getNumContacts() == 1);
		assertTrue(contactService.getContactList().elementAt(0)
				.getName().equals("Justin Smith"));
				
	}
	
	@Test
	void testGenerateUniqueIDLengthAndUniqueness() {
		ContactService service = new ContactService();
		String ID1 = service.generateUniqueId();
		String ID2 = service.generateUniqueId();
		assertEquals(10, ID1.length());
		assertEquals(10, ID2.length());
		assertNotEquals(ID1, ID2);
	}

}
