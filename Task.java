// CS 320 - Software Testing, Automation & Quality Analysis 
// Professor Kraya
// Kayla Macklin
// 09/23/2025
// 10/7/2025 - Project 6
// Module 4 Milestone

package taskApp;

public class Task {
	private final String taskID;
	private String taskName;
	private String taskDescription;
	
	// Constructors
	public Task(String taskID, String taskName, String taskDescription) {
		if (taskID == null || taskID.length() > 10) {
			throw new IllegalArgumentException("Error, task ID cannot be null and must be 10 characters or less.");
		}
		if (taskName == null || taskName.length() > 20) {
			throw new IllegalArgumentException("Error, task name cannot be null and must be 20 characters or less.");
		}
		if (taskDescription == null || taskDescription.length() > 50) {
			throw new IllegalArgumentException("Error, task description cannot be null and must be 50 characters or less.");
		}
		
		this.taskID = taskID;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}
	
	// Getters
	public String getTaskID() {
		return taskID;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	
	// Setters
	public void setTaskName(String taskName) {
		if (taskName == null || taskName.length() > 20) {
			throw new IllegalArgumentException("Error, task name cannot be null and must be 20 characters or less.");
		}
		this.taskName = taskName;
	}
	
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null || taskDescription.length() > 50) {
			throw new IllegalArgumentException("Error, task description cannot be null and must be 50 characters or less.");
		}
		this.taskDescription = taskDescription;
	}
}