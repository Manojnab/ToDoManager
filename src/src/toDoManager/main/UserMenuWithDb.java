package src.toDoManager.main;

import java.util.Scanner;

import src.toDoManager.main.dao.UserDaoWithDb;
import src.toDoManager.main.dao.UserDaoWithoutDb;
import src.toDoManager.main.entity.Task;

public class UserMenuWithDb {

	public static void userMenu()
	{
		// do not show the password
		System.out.println("1. Add task\n"
				+ "2. update task\n"
				+ "3. delete task\n"
				+ "4. search task\n"
				+ "5. see all tasks\n"
				+ "6. see completed tasks\n"
				+ "7. see incomplete tasks\n"
				+ "0. go to main menu");
	}

	public static void main(String[] args) {

		//CustomerDbConnection customerDb = new CustomerDbConnection();
		//CustomerService customerService = new CustomerService(customerDb);
		Scanner sc = new Scanner(System.in);
		String email, password, phone, city, name;
		int choice;
		UserDaoWithDb userDaoWithDb=new UserDaoWithDb();
		boolean flag = true;
		do {
			// Home page
			System.out.println("Choose one of the options");
			System.out.println("1. Login");
			System.out.println("2. Logout");
			System.out.println("0. Exit from the application");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Login");
				System.out.println("Enter email");
				email=sc.next();
				System.out.println("Enter password");
				password=sc.next();
				try {
					if(userDaoWithDb.login(email, password)) {
						userDashboard(sc,email);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Logout");
				break;
			case 0:
				System.out.println("exit from complete application");
				flag=false;
				break;
			default : System.out.println("Wrong option");
			}
		}while(flag);

	}

	

	public static void userDashboard(Scanner sc,String email)//, CustomerService customerService,String email)
	{
		boolean flag=true;
		//Customer currCustomer=new Customer();
		UserDaoWithDb userDaoWithDb=new UserDaoWithDb();
		try {
		
		//currCustomer=customerService.getCustomerByEmail(email);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		do {
			System.out.println("*******user dashboard**********");
		userMenu();
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("add task to the list");
			System.out.println("Enter task title");
			String title=sc.next();
			System.out.println("Enter task text");
			String text=sc.next();
			System.out.println("are u assigning the task to urself enter yes or no");
			String assignmentChoice=sc.next();
			if(assignmentChoice.equalsIgnoreCase("yes")) {
				try {
					userDaoWithDb.addTask(new Task(sequenceGenerator(),title, text, email, false));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Enter the email address of the assignee");
				String assigneeEmail=sc.next();
				try {
					userDaoWithDb.addTask(new Task(sequenceGenerator(),title, text, assigneeEmail, false));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			break;
		case 2:
			System.out.println("update existing task-----below is the list of existing task");
			try {
				System.out.println(userDaoWithDb.seeAllMyTasks(email));
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			System.out.println("enter the task id that needs to be updated");
			int id=sc.nextInt();
			System.out.println("Enter the assignee name");
			String newAssignee=sc.next();
			System.out.println("\nEnter new task text");
			String newText=sc.next();
			System.out.println("Enter completion status enter true or false");
			boolean newStatus=sc.nextBoolean();
			System.out.println("Enter new task titile\n");
			String newTitle=sc.next();
			try {
				userDaoWithDb.updateTask(new Task(id, newTitle, newText, newAssignee, newStatus));
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			break;
		case 3:
			System.out.println("delete a task-----below is the list of existing task");
			try {
				System.out.println(userDaoWithDb.seeAllMyTasks(email));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("Enter the id of the task that needs to be deleted");
			id=sc.nextInt();
			try {
				userDaoWithDb.deleteTask(id);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			break;
		case 4:
			System.out.println("search a task");
			System.out.println("Enter the id of the task");
			id=sc.nextInt();
			try {
				userDaoWithDb.searchById(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 5:
			System.out.println("see all my task");
			try {
				System.out.println(userDaoWithDb.seeAllMyTasks(email));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("see completed tasks");
			try {
				System.out.println(userDaoWithDb.seeAllMyCompletedTasks(email));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			System.out.println("see incomplete tasks");
			try {
				System.out.println(userDaoWithDb.seeAllMyInCompletedTasks(email));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 0:
			System.out.println("exit from application");
			flag=false;
			break;
			
			
		}
		}while(flag);
		
			
	}
	static int value=7;
	public static int sequenceGenerator() {
		return value++;
		
	}

}
