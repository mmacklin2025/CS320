// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 10/03/2025
// Module 5 Milestone

package appointmentApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService {
	private final ArrayList<Appointment> appointmentList = new ArrayList<>();
	
	// Display list of appointments
	public void displayAppointmentList() {
		for (Appointment appointment : appointmentList) {
			System.out.println("Appointment ID: " + appointment.getAppointmentID());
			System.out.println("Appointment Date: " + appointment.getAppointmentDate());
			System.out.println("Appointment Description: " + appointment.getAppointmentDescription());
		}
	}
	
	// Adds new appointment
	public void addAppointment(Date appointmentDate, String appointmentDescription) {
		Appointment appointment = new Appointment(appointmentDate, appointmentDescription);
		appointmentList.add(appointment);
	}
	
	public Appointment getAppointment(String appointmentID) {
		for (Appointment appointment : appointmentList) {
			if (appointment.getAppointmentID().equals(appointmentID)) {
				return appointment;
			}
		}
		return null;
	}
	
	// Deletes appointments
	public boolean deleteAppointment(String appointmentID) {
		for (Appointment appointment : appointmentList) {
			if (appointment.getAppointmentID().equals(appointmentID)) {
				appointmentList.remove(appointment);
				return true;
			}
		}
		return false;
	}	
	
	// Updates the appointment date
	public boolean updateAppointmentDate(String appointmentID, Date updatedDate) {
		Appointment appointment = getAppointment(appointmentID);
		if (appointment != null) {
			appointment.setAppointmentDate(updatedDate);
			return true;
		}
		return false;
	}
	
	// Updates the appointment description
	public boolean updateAppointmentDescription(String appointmentID, String updatedDescription) {
		Appointment appointment = getAppointment(appointmentID);
		if (appointment != null) {
			appointment.setAppointmentDescription(updatedDescription);
			return true;
		}
		return false;
	}
	
	public List<Appointment> getAppointments() {
		return new ArrayList<>(appointmentList);
	}
}