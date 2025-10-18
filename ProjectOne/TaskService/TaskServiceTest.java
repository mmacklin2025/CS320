// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/23/2025
// Module 4 Milestone

package taskApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
	
	@Test
	void testAddTask() {
		TaskService service = new TaskService();
		service.addTask("Task Name", "Task Description");
		assertEquals(1, service.getTaskCount());
		assertFalse(service.getTaskList().isEmpty());
		Task task = service.getTaskList().get(0);
		assertEquals("Task Name", task.getTaskName());
		assertEquals("Task Description", task.getTaskDescription());
		assertNotNull(task.getTaskID());
		assertTrue(task.getTaskID().length() <= 10);
	}
	
	@Test
	void testAddInvalidTask() {
		TaskService service = new TaskService();
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask(null, "Valid Description");
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask("Valid Name", null);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask("NameThatIsWayTooLongToBeValid", "Valid Description");
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask("Valid Name", "This is a description that is way too long to be a valid description, it will catch an error.");
		});
	}
	
	@Test
	void testDeleteTask() {
		TaskService service = new TaskService();
		service.addTask("Task Name", "Task Description");
		Task task = service.getTaskList().get(0);
		
		// remove task with valid ID
		service.deleteTask(task.getTaskID());
		assertEquals(0, service.getTaskCount());
		assertTrue(service.getTaskList().isEmpty());
		
		// remove task with invalid ID
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask(null); // null ID
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask("12345678901"); // ID too long
		});
		
		// remove task from an empty list
		service.deleteTask("non-existant task");
	}
	
	@Test
	void testUpdateFields() {
		TaskService service = new TaskService();
		service.addTask("Task Name", "Task Description");
		Task task = service.getTaskList().get(0);
		
		// update task name
		service.updateTask(task.getTaskID(), "Updated Name", 1);
		assertEquals("Updated Name", service.getTaskList().get(0).getTaskName());
		
		// update task description
		service.updateTask(task.getTaskID(), "Updated Description", 2);
		assertEquals("Updated Description", service.getTaskList().get(0).getTaskDescription());
		
		// invalid update - null task name
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask(null, "Description", 1);
		});
		
		// invalid update - null task description
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask(task.getTaskID(), null, 1);
		});
		
		// invalid update
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask(task.getTaskID(), "Update", -1);
		});
		
		// invalid update - task name too long
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask(task.getTaskID(), "NameThatIsWayTooLongToBeValid", 1);
		});
		
		// invalid update - task description too long
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask(task.getTaskID(), "Description that is way too long to be valid and will definitely cause an error. This description will definitely exceed the character limit.", 2);
		});
		
		// invalid update - selection doesn't exist
		service.updateTask(task.getTaskID(), "Name", 99);
	}
	
	@Test
	void testGenerateUniqueID() {
		TaskService service = new TaskService();
		String ID1 = service.generateUniqueID();
		String ID2 = service.generateUniqueID();
		
		assertNotNull(ID1);
		assertNotNull(ID2);
		assertTrue(ID1.length() <= 10);
		assertTrue(ID2.length() <= 10);
		assertNotEquals(ID1, ID2);
	}
}