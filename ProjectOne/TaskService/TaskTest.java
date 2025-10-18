// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/23/2025
// Module 4 Milestone

package taskApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {
	@Test
	void testTaskConstructor() {
		//task ID is too long
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task("1234567890A", "TaskName", "TaskDescription");
		});
		
		//task name too long
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task("1234567890", "TaskName123456789", "TaskDescription");
		});
		
		//task description too long
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task("1234567890", "TaskName", "TaskDescriptionTaskDescriptionTaskDescription" + "TaskDescriptionTaskDescriptionTaskDescriptionTaskDescription");
		});
		
		//null task ID
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task(null, "TaskName", "TaskDescription");
		});
		
		// null task name
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task("1234567890", null, "TaskDescription");
		});
		
		//null task description
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Task("1234567890", "TaskName", null);
		});
	}
	
	@Test
	void testTaskConstructorAndGetters() {
		Task task = new Task("1234567890", "Task Name", "Task Description");
		assertTrue(task.getTaskID().equals("1234567890"));
		assertTrue(task.getTaskName().equals("Task Name"));
		assertTrue(task.getTaskDescription().equals("Task Description"));
	}
	
	@Test
	void testTaskSetters() {
		Task task = new Task("1234567890", "Task Name", "Task Description");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			task.setTaskName(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			task.setTaskName("This Name is Waaaaay Too Long");
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			task.setTaskDescription(null);
		});
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			task.setTaskDescription("This is a task description that's really"
					+ "way too long to be valid so it throws an error");
		});
		task.setTaskName("New Name");
		task.setTaskDescription("New Description");
		assertTrue(task.getTaskName().equals("New Name"));
		assertTrue(task.getTaskDescription().equals("New Description"));
	}
}