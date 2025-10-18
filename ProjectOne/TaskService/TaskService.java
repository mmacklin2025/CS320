// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/23/2025
// Module 4 Milestone

package taskApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TaskService {
	
	private List<Task> taskList = new ArrayList<>();
	
	// adds a new task
	public void addTask(String taskName, String taskDescription) {
		String taskID = generateUniqueID();
		Task newTask = new Task(taskID, taskName, taskDescription);
		taskList.add(newTask);
	}
	
	// deletes a task using ID
	public void deleteTask(String taskID) {
		if (taskID == null || taskID.length() > 10) {
			throw new IllegalArgumentException("Error, task ID invalid.");
		}
		
		Task taskRemove = findTaskByID(taskID);
		if (taskRemove != null) {
			taskList.remove(taskRemove);
		}
		
		else {
			System.out.println("Error, task could not be found.");
		}
	}
	
	// updates task name or description
	public void updateTask(String taskID, String updateValue, int fieldSelector) {
		if (taskID == null || taskID.length() > 10 || updateValue == null || fieldSelector < 1 || fieldSelector > 2) {
			throw new IllegalArgumentException("Error, invalid update parameters.");
		}
		
		Task taskToUpdate = findTaskByID(taskID);
		if (taskToUpdate == null) {
			System.out.println("Error, task could not be found.");
			return;
		}
		
		switch (fieldSelector) {
		case 1:
			taskToUpdate.setTaskName(updateValue);
			break;
		case 2:
			taskToUpdate.setTaskDescription(updateValue);
			break;
		}
		
	}
	
	// generates a unique ID
	public String generateUniqueID() {
		Random rand = new Random();
		while (true) {
			String id = String.valueOf(rand.nextInt(1_000_000_000));
			boolean isUnique = taskList.stream().noneMatch(t -> t.getTaskID().equals(id));
			if (id.length() <= 10 && isUnique) {
				return id;
			}
		}
	}
	
	// find a task using ID
	private Task findTaskByID(String taskID) {
		return taskList.stream()
				.filter(t -> t.getTaskID().equals(taskID))
				.findFirst()
				.orElse(null);
	}
	
	// get task list
	public List<Task> getTaskList() {
		return taskList;
	}
	
	// get task count
	public int getTaskCount() {
		return taskList.size();
	}
}

