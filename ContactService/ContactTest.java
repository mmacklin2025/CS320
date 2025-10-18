// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/17/2025
// Module 3 Milestone

package contactApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ContactTest {
	
	@Test
	void testContactNullArguments() {
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			new Contact(null, null, null, null, null);
		});
	}
	
	@Test
	void testContactAndGetters() {
		Contact contact = new Contact("654321", "Elliott", "Matthews", "7147814000", "871 Lemon Ave.");
		assertTrue(contact.getName().equals("Elliott Matthews"));
		assertTrue(contact.getPhoneNumber().equals("7147814000"));
		assertTrue(contact.getContactAddress().equals("871 Lemon Ave."));
		assertTrue(contact.getContactID().equals("654321"));
	}
	
	@Test
	void testSetFirstAndLastName() {
		Contact contact = new Contact("654321", "Elliott", "Matthews", "7147814000", "871 Lemon Ave.");
		contact.setFirstName("Elliott");
		contact.setLastName("Matthews");
		assertTrue(contact.getName().equals("Elliott Matthews"));
	}
	
	@Test
	void testSetPhoneNumberAndAddress() {
		Contact contact = new Contact("654321", "Elliott", "Matthews", "7147814000", "871 Lemon Ave.");
		contact.setPhoneNumber("7147814000");
		contact.setContactAddress("871 Lemon Ave.");
		assertTrue(contact.getPhoneNumber().equals("7147814000"));
		assertTrue(contact.getContactAddress().equals("871 Lemon Ave."));
	}
	
	@Test
	void testNullSetAttributes() {
		Contact contact = new Contact("654321", "Elliott", "Matthews", "7147814000", "871 Lemon Ave.");
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contact.setFirstName(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contact.setLastName(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contact.setContactAddress(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contact.setPhoneNumber(null);
		});
	}
	
	@Test
	void testAllGetters() {
		Contact contact = new Contact("654321", "Elliott", "Matthews", "7147814000", "871 Lemon Ave.");
		assertTrue(contact.getName().equals("Elliott Matthews"));
		assertTrue(contact.getContactID().equals("654321"));
		assertTrue(contact.getPhoneNumber().equals("7147814000"));
		assertTrue(contact.getContactAddress().equals("871 Lemon Ave."));
	}
	
	@Test 
	void testMaxLengthFields() {
		Contact contact = new Contact("1234567890", "Firstname", "Lastname", "1234567890", "123456789012345678901234567890");
		assertEquals("Firstname Lastname", contact.getName());
		assertEquals("1234567890", contact.getPhoneNumber());
		assertEquals("123456789012345678901234567890", contact.getContactAddress());
	}
	
	@Test 
	void testTooLongFields() {
		// ID too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345678901", "First", "Last", "1234567890", "Address");
		});
		
		// First name too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1234567890", "Firstnametoolong", "Last", "1234567890", "Address");
		});
		
		// Last name too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1234567890", "First", "Lastnametoolong", "1234567890", "Address");
		});
		
		// Phone number too short
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1234567890", "First", "Last", "123456789", "Address");
		});
		
		// Phone number too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1234567890", "First", "Last", "12345678901", "Address");
		});
		
		// Address too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1234567890", "First", "Last", "1234567890", "1234567890123456789012345678901");
		});
	}
}
