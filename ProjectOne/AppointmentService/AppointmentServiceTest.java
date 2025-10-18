// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 10/03/2025
// Module 5 Milestone

package appointmentApp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {
	private Date futureDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year,  month, day);
		return cal.getTime();
	}
	
	@Test
	@DisplayName("Test to add an appointment")
	void testAddAppointment() {
        AppointmentService service = new AppointmentService();
        Date date = futureDate(2025, Calendar.DECEMBER, 25);
        service.addAppointment(date, "Holiday meeting");

        Appointment added = service.getAppointments().get(0); // First ID is usually "0"
        assertNotNull(added);
        assertEquals("Holiday meeting", added.getAppointmentDescription());
        assertEquals(date, added.getAppointmentDate());
    }

	@Test
	@DisplayName("Test to update appointment date.")
	void testUpdateAppointmentDate() {
        AppointmentService service = new AppointmentService();
        Date originalDate = futureDate(2025, Calendar.JANUARY, 1);
        service.addAppointment(originalDate, "Initial");

        Appointment appt = service.getAppointments().get(0);
        String id = appt.getAppointmentID();
        Date newDate = futureDate(2026, Calendar.MARCH, 15);
        boolean updated = service.updateAppointmentDate(id, newDate);

        assertTrue(updated);
        assertEquals(newDate, appt.getAppointmentDate());
    }
	
	@Test
	@DisplayName("Test to update the appointment description.")
	void testUpdateAppointmentDescription() {
        AppointmentService service = new AppointmentService();
        service.addAppointment(futureDate(2025, Calendar.JANUARY, 1), "Initial");

        Appointment appt = service.getAppointments().get(0);
        String id = appt.getAppointmentID();
        boolean updated = service.updateAppointmentDescription(id, "Updated Description");

        assertTrue(updated);
        assertEquals("Updated Description", appt.getAppointmentDescription());
    }
	
	@Test
	@DisplayName("Test to ensure that appointment service correctly deletes appointments.")
	void testDeleteAppointment() {
        AppointmentService service = new AppointmentService();
        service.addAppointment(futureDate(2025, Calendar.JANUARY, 1), "To be deleted");

        String id = service.getAppointments().get(0).getAppointmentID();
        boolean deleted = service.deleteAppointment(id);
        
        assertTrue(deleted);
        assertNull(service.getAppointment(id));
    }

	@Test
	@DisplayName("Test to ensure that appointment service can reject invalid appointment dates.")
	void testInvalidDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 100000); // Past date
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(pastDate, "Past date");
        });
    }
	
	@Test
	@DisplayName("Test to ensure that the appointment service can reject null descriptions.")
	void testNullDescription() {
        Date future = futureDate(2025, Calendar.JANUARY, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(future, null);
        });
    }
	
	@Test
	@DisplayName("Test to ensure that the appointment service can reject descriptions that are too long.")
	void testLongDescription() {
        Date future = futureDate(2025, Calendar.JANUARY, 1);
        String longDesc = "This description is definitely longer than fifty characters and should be rejected.";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(future, longDesc);
        });
    }
	
	@Test
    @DisplayName("Fails to update non-existent appointment.")
    void testUpdateNonExistentAppointment() {
        AppointmentService service = new AppointmentService();
        boolean updated = service.updateAppointmentDate("nonexistentID", futureDate(2025, Calendar.JANUARY, 1));
        assertFalse(updated);
    }
	
	@Test
    @DisplayName("Fails to delete non-existent appointment")
    void testDeleteNonExistentAppointment() {
        AppointmentService service = new AppointmentService();
        boolean deleted = service.deleteAppointment("nonexistentID");
        assertFalse(deleted);
    }
}