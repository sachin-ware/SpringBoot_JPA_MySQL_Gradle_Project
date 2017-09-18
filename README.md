"# SpringBoot_JPA_MySQL_Gradle_Project" 

Steps used to create this project:

Steps to create SpringBoot applcition with JPA and MySQL database.

1.)Go to:https://start.spring.io
2)	Give an artifact_id(This will become your project name).
3)select type as gradle or maven 
4) Fill the other project related details and click on dependencies button to show available dependencies.
5) Select dependancies:
		1.web: for web project
		2.lombok:for getter and setter, constructor  annotations
		3.JPA:Java Persistence API including spring-data-jpa, spring-orm and Hibernate
		4.MySQL:MySQL jdbc driver
		
6)Click on Generate project->Save it and extract on disc->
7)go to extrected projects root directory and fire "gradlew eclipse" command, it will create .project file and make project eclipse compatible.
8)Import extracted project in eclipse as general project in case of gradle type,otherwise import as maven project.

Now you can test by running the project as java application using right click and selecting application class.

9)Now to configure MySQL with this project put following code in src/main/resources/application.properties file
		server.port=8081 //runs application in this port
		# Show or not log for each sql query
		spring.jpa.show-sql = true  
		#MYSQL
		spring.datasource.url=jdbc:mysql://localhost:3306/sachintestdb
		spring.datasource.username=root
		spring.datasource.password=root
		spring.datasource.driverClassName=com.mysql.jdbc.Driver
		spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

		Make sure that database is crated with provided name username and password.
		(We assume we have created db with name sachintestdb and table  "create table User(user_id int(2) primary key,user_name varchar(30),password varchar(30))" )
		
10)Create a domain class. say User and put following annotations


		package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains;
		import java.io.Serializable;
		import javax.persistence.Column;
		import javax.persistence.Entity;
		import javax.persistence.GeneratedValue;
		import javax.persistence.GenerationType;
		import javax.persistence.Id;
		import javax.persistence.Table;
		import lombok.Data;
		//@Getter //Defines the getter methods for all data members in the class internally,they are not visible in the code;
		//@Setter//Defines the setter methods for all data members in the class internally,they are not visible in the code;
		//@AllArgsConstructor//Defines All argument constructor 
		//@NoArgsConstructor//Defines No argument constructor
		@Entity
		@Table(name="User")
		@Data
		public class User implements Serializable {
			
			@Id  //this field is necessory for primary key mapping .
			@GeneratedValue(strategy=GenerationType.AUTO)//This will set primary key value automatically if user does not pass it.But berore using this we need to set primary key as AUTO Increment in database table while creating table.
			@Column(name = "user_id")
			Integer userId;
			
			@Column(name = "user_name")
			String userName;
			@Column(name = "password")
			String password;
			
			
			public Integer getUserId() {
				return userId;
			}
			public void setUserId(Integer userId) {
				this.userId = userId;
			}
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
			
			@Override
			public String toString() {
				return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
			}
		}


11)Create Repository class like:

		package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories;

		import org.springframework.data.jpa.repository.JpaRepository;
		import org.springframework.stereotype.Repository;

		import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;

		@Repository
		public interface UserRepository extends JpaRepository<User, Integer>{

				
		}


12)Create Service class like:
		package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services;
		import java.util.ArrayList;
		import java.util.List;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Service;
		import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
		import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories.UserRepository;

		@Service
		public class UserService {
			@Autowired
			UserRepository userRepository;
			public List<User> getAllUsers(){

				 List<User> userList=new ArrayList<>();
				try {
					userList=userRepository.findAll();
					
				} catch (Exception e) {
					System.out.println("Exception in UserRepostory:"+e.getMessage());
				}
				return userList;
			}
			public User saveUserToDb(User user) {
				User usr=new User();
				try {
					usr=userRepository.save(user);
				} catch (Exception e) {
					System.out.println("Exception in seveUserToDb:"+e.getMessage());
				}
				return usr;
			}
			public User getUserById(Integer id) {
				User usr=new User();
				try {
					usr=userRepository.findOne(id);
				} catch (Exception e) {
					System.out.println("Exception in getUserById:"+e.getMessage());
				}
				return usr;
			}

		}


13)Create Controller class like:

			package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.rest_controllers;

			import java.util.List;

			import org.springframework.beans.factory.annotation.Autowired;
			import org.springframework.web.bind.annotation.GetMapping;
			import org.springframework.web.bind.annotation.PathVariable;
			import org.springframework.web.bind.annotation.PostMapping;
			import org.springframework.web.bind.annotation.RequestBody;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RestController;

			import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
			import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService;

			@RestController
			@RequestMapping("/")
			public class UserRestController {
				@Autowired
				private UserService userService;
				
				@GetMapping("/users")
				public List<User> getAllUsers(){
					List<User> userList=null;
					try {
						userList=userService.getAllUsers();
						System.out.println(userList);
					} catch (Exception e) {
						System.out.println("Exception in GetAllUsers:"+e.getMessage());
					}
					return  userList;
				}
				
				
				@GetMapping("/")
				public String home(){
					return "This is Default response from SpringBootJpaMySqlGradleProjectApplication !!!";
				}
				
				
				@GetMapping("/user/{id}")
				public User getUser(@PathVariable Integer id){
					User tempUser=null;
					try {
						tempUser=userService.getUserById(id);
					} catch (Exception e) {
						System.out.println("EXCEPTION getAllUsers in UserRestController:-----------------------------\n"+e.getMessage());
					}
					return tempUser;
				}
				
				
				@PostMapping("/user")
				public User saveUser(@RequestBody User user){
					User tempUser=null;
					try {
						tempUser=userService.saveUserToDb(user);
						
					} catch (Exception e) {
						System.out.println("EXCEPTION saveUser in UserRestController:-----------------------------\n"+e.getMessage());
					}
					return tempUser;
				}
				

			}





