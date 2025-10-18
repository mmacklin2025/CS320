// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 10/07/2025 - version 2.0
// Module 5 Milestone

package appointmentApp;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Date;

public class Appointment {
	private final String appointmentID;
	private Date appointmentDate;
	private String appointmentDescription;
	private static final AtomicLong IDGenerator = new AtomicLong();
	
	// Constructors
	public Appointment(Date appointmentDate, String appointmentDescription) {
		String generatedID = String.valueOf(IDGenerator.getAndIncrement());
		if (generatedID.length() > 10) {
			throw new IllegalArgumentException("Error, appointment ID must be 10 characters or less.");
		}
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Error, please choose a valid date.");
		}
		if (appointmentDescription == null || appointmentDescription.length() > 50) {
			throw new IllegalArgumentException("Error, appointment description must be 50 characters or less.");
		}
		
		this.appointmentID = generatedID;
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
	}
	
	// Getters
	public String getAppointmentID() {
		return appointmentID;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getAppointmentDescription() {
		return appointmentDescription;
	}
	
	// Setters
	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Error, please choose a valid date.");
		}
		this.appointmentDate = appointmentDate;
	}

	public void setAppointmentDescription(String updatedString) {
		if (updatedString == null || updatedString.length() > 50) {
			throw new IllegalArgumentException("Error, please enter a valid description.");
		}
		this.appointmentDescription = updatedString;		
	}
}