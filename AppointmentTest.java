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

class AppointmentTest {
	private Date futureDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}
	
	@Test
	@DisplayName("Appointment Description cannot have more than 50 characters.")
	void testLongDescription() {
        Date future = futureDate(2025, Calendar.JANUARY, 1);
        String longDesc = "This description is definitely longer than fifty characters and should be rejected.";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(future, longDesc);
        });
    }

	@Test
	@DisplayName("Appointment Date cannot be before the current date.")
	void testPastDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 100000); // 100 seconds ago
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(pastDate, "Valid description");
        });
    }

	@Test
	@DisplayName("Appointment Date cannot be null.")
	void testNullDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, "Valid description");
        });
    }
	
	@Test
	@DisplayName("Appointment Description cannot be null.")
	void testNullDescription() {
        Date future = futureDate(2025, Calendar.JANUARY, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(future, null);
        });
    }
	
	@Test
	@DisplayName("Appointment is valid.")
	void testValidAppointment() {
        Date future = futureDate(2025, Calendar.JANUARY, 1);
        Appointment appt = new Appointment(future, "Short description");
        assertNotNull(appt);
        assertEquals("Short description", appt.getAppointmentDescription());
        assertEquals(future, appt.getAppointmentDate());
        assertTrue(appt.getAppointmentID().length() <= 10);
    }
}