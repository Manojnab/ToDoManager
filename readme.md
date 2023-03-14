1. This project has two main class
	a.UserMenu - this class deals with all the CRUD Operation without DB i.e using List 		for storing users and task.
	b.UserMenuWithDb - This class deals with the CRUD operations on MySql DB.
2. dao class has db connection and other two classes where one class(UserDaoWithDb) deals with db and other(UserDaoWithoutDb) works without DB.
3. entity has two POJO classes Task and User.
4. run UserMenu as java application to test implementation without DB.
5. run USerMenuWithDb as java application to test implementation with DB.