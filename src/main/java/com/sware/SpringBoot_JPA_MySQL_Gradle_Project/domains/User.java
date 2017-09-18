package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
