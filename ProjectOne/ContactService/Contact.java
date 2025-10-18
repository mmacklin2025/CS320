// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/17/2025
// Module 3 Milestone

package contactApp;

public class Contact {
	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String contactAddress;
	
	// constructor
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String contactAddress) {
		if (contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Contact ID invalid");
		}
		
		if (firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("First name invalid");
		}
		
		if (lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Last name invalid");
		}
		
		if (phoneNumber == null || phoneNumber.length() > 10 || phoneNumber.length() < 10) {
			throw new IllegalArgumentException("Phone number invalid");
		}
		
		if (contactAddress == null || contactAddress.length() > 30) {
			throw new IllegalArgumentException("Contact address invalid");
		}
		
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.contactAddress = contactAddress;
	}
	
	// getters
	public final String getContactID() {
		return this.contactID;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getContactAddress() {
		return this.contactAddress;
	}
	
	// setters	
	public void setFirstName(String newFirstName) {
		if (newFirstName == null || newFirstName.length() > 10) {
			throw new IllegalArgumentException("First name invalid");
		}
		
		firstName = newFirstName;
	}
	
	public void setLastName(String newLastName) {
		if (newLastName == null || newLastName.length() > 10) {
			throw new IllegalArgumentException("Last name invalid");
		}
		
		lastName = newLastName;
	}
	
	public void setPhoneNumber(String newPhoneNumber) {
		if (newPhoneNumber == null || newPhoneNumber.length() > 10 || newPhoneNumber.length() < 10) {
			throw new IllegalArgumentException("Phone number invalid");
		}
		
		phoneNumber = newPhoneNumber;
	}
		
	public void setContactAddress(String newContactAddress) {
		if (newContactAddress == null || newContactAddress.length() > 30) {
			throw new IllegalArgumentException("Contact address invalid");
		}
		
		contactAddress = newContactAddress;
	}
	
	
}