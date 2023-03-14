package toDoManager.main.dao;

import java.util.ArrayList;
import java.util.List;

import toDoManager.main.entity.Task;
import toDoManager.main.entity.User;

public class UserDaoWithoutDb {
	List<User> userList=new ArrayList<User>();
	List<Task> taskList=new ArrayList<Task>();
	public UserDaoWithoutDb() {
		userList.add(new User("manojna", "manojna@gmail.com", "asdf@123"));
		userList.add(new User("manojnab", "manojnab@gmail.com", "asdf@123"));
		userList.add(new User("bmanojna", "bmanojna@gmail.com", "asdf@123"));
		taskList.add(new Task(1, "core java","assignment1" , "manojna@gmail.com", false));
		taskList.add(new Task(2, "Hibernate","assignment1" , "manojnab@gmail.com", false));
		taskList.add(new Task(3, "spring mvc","assignment1" , "manojnab@gmail.com", false));
		taskList.add(new Task(4, "springboot","assignment1" , "manojna@gmail.com", false));
		taskList.add(new Task(5, "jdbc","assignment1" , "bmanojna@gmail.com", false));
		taskList.add(new Task(6, "spring","assignment1" , "bmanojna@gmail.com", false));
		
	}
	public List<Task> addTask(Task task) throws Exception {
		for(Task tasks:taskList ) {
			if(task.getTaskId()==tasks.getTaskId()) {
				throw new Exception("task cannot be added task with same id exists. try with different id");
				
			}
		}
		taskList.add(task);
		return taskList;
	}
	public List<Task> updateTask(Task task){
			for(int i=0;i<taskList.size();i++){ 
				if(taskList.get(i).getTaskId()==task.getTaskId()) {
					//tasks=task;
					taskList.set(i, task);
					
				}
			}
			//taskList.add(task);
			return taskList;
	}
	public List<Task> deleteTask(int id){
		for(Task tasks:taskList ) {
			if(id==tasks.getTaskId()) {
				taskList.remove(tasks);
				break;
				
			}
		}
		
		return taskList;
	}
	public Task searchById(int id) {
		for(Task tasks:taskList ) {
			if(id==tasks.getTaskId()) {
				return tasks;
				
			}
		}
		
		return null;
	}
	public List<Task> seeAllMyTasks(String email) {
		List<Task> seeAllTasks=new ArrayList<Task>();
		for(Task tasks:taskList ) {
			if(email.equals(tasks.getAssignedTo())) {
				seeAllTasks.add(tasks);
				
			}
		}
		
		return seeAllTasks;
	}
	public List<Task> seeAllMyCompletedTasks(String email) {
		List<Task> seeAllCompletedTasks=new ArrayList<Task>();
		for(Task tasks:taskList ) {
			if(email.equals(tasks.getAssignedTo())&&tasks.getTaskCompleted()==true) {
				seeAllCompletedTasks.add(tasks);
				
			}
		}
		
		return seeAllCompletedTasks;
	}
	public List<Task> seeAllMyInCompletedTasks(String email) {
		List<Task> seeAllInCompletedTasks=new ArrayList<Task>();
		for(Task tasks:taskList ) {
			if(email.equals(tasks.getAssignedTo())&&tasks.getTaskCompleted()==false) {
				seeAllInCompletedTasks.add(tasks);
				
			}
		}
		
		return seeAllInCompletedTasks;
	}
	public boolean login(String email, String password)
	{
		for(User c : userList )
		{
			if(c.getEmail().equals(email))
			{
				if(c.getPassword().equals(password))
					return true;
			}
		}
		return false;
	} 
	

}
