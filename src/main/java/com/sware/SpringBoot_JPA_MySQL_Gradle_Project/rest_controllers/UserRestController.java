package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.rest_controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
//import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService;

@RestController
@CrossOrigin//This annotation allows client browser to get data successfully by  hitting endpoints from other port than server's port. 
@RequestMapping("/")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	// Following Endpoint is created to upload mu ltipart file.
	//To Test this endpoint use postman and select key-type as file and choose any file in body tab for post request with given url.
	//Here @RequestBody did not worked.
	@PostMapping("/uploadPic")
	//public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile multipartFile){
		public String upload(@RequestParam("file") MultipartFile multipartFile){
		String cloudinaryImgURL=null;
		try {
			File fileDir = new File("rowFiles");
		    if (! fileDir.exists()){
		    	fileDir.mkdir();
		    }
		    String fileName=multipartFile.getOriginalFilename();
			File physicalFile=new File(multipartFile.getOriginalFilename());
			FileOutputStream fout=new FileOutputStream(fileDir.getName()+"/"+physicalFile);
			fout.write(multipartFile.getBytes());
			fout.close();
			File toUpload = new File("rowFiles/"+fileName);
			Cloudinary cloudinary = new Cloudinary();
			System.out.println("API Key:"+cloudinary.config.apiKey);
			Map params = ObjectUtils.asMap("public_id", "SRWRestImageBase/"+fileName);
			Map uploadResult = cloudinary.uploader().upload(toUpload, params);
			toUpload.delete();
			System.out.println("==============>>"+uploadResult.get("url"));
			cloudinaryImgURL=uploadResult.get("url").toString();
		} catch (Exception e) {
			System.out.println("upload:"+e.getMessage());
		}
		return cloudinaryImgURL;
	}
	
	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> userList=null;
		try {
			userList=userService.getAllUsers();
		//	Integer n=10/0;
			System.out.println(userList);
		} catch (Exception e) {
			System.out.println("Exception in GetAllUsers:"+e.getMessage());
		}
		return  userList;
	}
	
	public void writeToConsole(String s) {
		System.out.println("<<<<<<<<<<<<<< "+s+" >>>>>>>>>>>>>>");
	}
	
	@GetMapping("/")
	public String home(){
		writeToConsole("Visited Sachin Rest Server");
		return "<div style='height:900px;width:100%;padding-left:20%;background-color:lavender;'>"
				//+ "background-image: url(data:image/png;base64,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);'>  "
				+ "<img src='data:image/png;base64,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'/>"
				+ "<h3 style='color:red;'>Hi, welcome to SRW Rest Server . . </h3> <br><br>"
				+ "<h4>Folowing are our endpoints:\n </h4><br>"
				+"<b>1. GET : /users - For all users </b><br>"
				+"<b>2. GET : /user/{id} - specific user </b><br>"
				+"<b>3. POST : /user - Save user </b><br>"
				+"<b>4. DELETE : /user/{id} - delete user </b><br>"
				+"<b>5. /user/getuserbyemailid/{emailid:.+} :find user by email id </b><br>."
				+ "<h3><a href='https://sachin-ware-sb-rest-server.herokuapp.com/swagger-ui.html'>Click to see swagger exposed endpoint.</a></h3>"
				
				+"<p>This is Default response from SpringBootJpaMySqlGradleProjectApplication !!! </p>"
				+ "</div>";
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
			System.out.println("SRW-LOGS===> user created:"+tempUser.toString());
			
		} catch (Exception e) {
			System.out.println("EXCEPTION saveUser in UserRestController:-----------------------------\n"+e.getMessage());
		}
		return tempUser;
	}
	
	@PostMapping("/users")
	public List<User> saveMultipleUser(@RequestBody User[] users){
		List<User>userList=null;
		try {
			userList=Arrays.asList(userService.saveMultipleUsersToDb(users));
			
		} catch (Exception e) {
			System.out.println("EXCEPTION saveMultipleUser in UserRestController:-----------------------------Changed test\n"+e.getMessage());
		}
		return userList;
	}
	
	
	
	
	@GetMapping("/user/getuserbyemailid/{emailid:.+}")  //':.+' is used to get data after . symbol as well. e.g. sachin@gmail.com (http://localhost:8081/user/getuserbyemailid/user3@gmail.com)
	public User findUserByEmailId(@PathVariable String emailid){
		User tempUser=null;
		try {
			tempUser=userService.findUserByEmailAddress(emailid);
			
		} catch (Exception e) {
			System.out.println("EXCEPTION saveUser in findUserByEmailId:-----------------------------\n"+e.getMessage());
		}
		return tempUser;
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user){
		User usr=userService.updateUser(user);
		return usr;
	}
	
	@GetMapping("/user/bylastname/{fName}")
	
	public List<User> updateUser(@PathVariable String fName){
		List<User> usr=userService.findByUserFirstName(fName);
		return usr;
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer userId) {
		String res=null;
		try {
			userService.deleteUser(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "User Deleted Successfully . . .";
	}
	

}
