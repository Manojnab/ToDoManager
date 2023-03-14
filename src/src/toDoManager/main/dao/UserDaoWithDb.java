package src.toDoManager.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.toDoManager.main.entity.Task;
import src.toDoManager.main.entity.User;

public class UserDaoWithDb {
	Connection conn = DbConnection.getConnection();
	public boolean addTask(Task task) throws Exception {
			// SQL injection attacks - FYI
			// 1 2
			String sql = "insert into task values(?,?,?,?,?)";
			System.out.println(sql);
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, task.getTaskId());
				statement.setString(2, task.getTaskTitle());
				statement.setString(3,task.getTaskText());
				statement.setString(4, task.getAssignedTo());
				statement.setBoolean(5, task.getTaskCompleted());

				statement.execute();
			}
			catch(SQLException ex)
			{
				System.out.println("ERROR");
				throw new Exception(ex.getMessage());
			}


			return true;
	}
	public boolean updateTask(Task task) throws Exception{
		String sql = "update task set tasktitle =?, tasktext=?,assignedto=?,taskcompleted=? where taskid=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(5, task.getTaskId());
			statement.setString(1, task.getTaskTitle());
			statement.setString(2,task.getTaskText());
			statement.setString(3, task.getAssignedTo());
			statement.setBoolean(4, task.getTaskCompleted());
			statement.execute();
		}
		catch(SQLException ex)
		{
			System.out.println("ERROR");
			throw new Exception(ex.getMessage());
		}

		return true;
}
	public boolean deleteTask(int id) throws Exception{
		String sql = "delete from task where taskid=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		}
		catch(SQLException ex)
		{
			System.out.println("ERROR");
			throw new Exception(ex.getMessage());
		}
		return true;
	}
	public Task searchById(int id) throws Exception {
		Task task = null;
		String sql = "select * from task where taskid=?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				task = new Task();
				task.setTaskId(rs.getInt(1));
				task.setTaskTitle(rs.getString(2));
				task.setTaskText(rs.getString(3));
				task.setAssignedTo(rs.getString(4));
				task.setTaskCompleted(rs.getBoolean(5));

			}
			else
				throw new Exception("No customer with "+id+" found");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return task;
	}
	public List<Task> seeAllMyTasks(String email) throws Exception {
		String sql = "select * from task where assignedto=?";
		/**
		 * 1. get DB connection
		 */
		//2. create statements
		List<Task> task = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Task t1 = new Task();
				t1.setTaskId(rs.getInt(1));
				t1.setTaskTitle(rs.getString(2));
				t1.setTaskText(rs.getString(3));
				t1.setAssignedTo(rs.getString(4));
				t1.setTaskCompleted(rs.getBoolean(5));
				task.add(t1);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return task;
	}
	public List<Task> seeAllMyCompletedTasks(String email) throws Exception {
		List<Task> seeAllCompletedTasks=new ArrayList<Task>();
		String sql = "select * from task where assignedto=? and taskcompleted=1";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Task t1 = new Task();
				t1.setTaskId(rs.getInt(1));
				t1.setTaskTitle(rs.getString(2));
				t1.setTaskText(rs.getString(3));
				t1.setAssignedTo(rs.getString(4));
				t1.setTaskCompleted(rs.getBoolean(5));
				seeAllCompletedTasks.add(t1);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
		return seeAllCompletedTasks;
	}
	public List<Task> seeAllMyInCompletedTasks(String email) throws Exception {
		List<Task> seeAllInCompletedTasks=new ArrayList<Task>();
		Task t1=null;
		String sql = "select * from task where assignedto=? and taskcompleted=0";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				t1 = new Task();
				t1.setTaskId(rs.getInt(1));
				t1.setTaskTitle(rs.getString(2));
				t1.setTaskText(rs.getString(3));
				t1.setAssignedTo(rs.getString(4));
				t1.setTaskCompleted(rs.getBoolean(5));
				seeAllInCompletedTasks.add(t1);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
		return seeAllInCompletedTasks;
	}
	public boolean login(String email, String password) throws Exception
	{
		User user = null;
		String sql = "select password from user where email=?";
		//select password from customer where email='sh@g.c'
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				if(password.equals(rs.getString(1)))
					return true;
			}
			throw new Exception("Invalid credentials");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	/*static int value=7;
	public static int sequenceGenerator() {
		return value++;
		
	}*/
	
}
