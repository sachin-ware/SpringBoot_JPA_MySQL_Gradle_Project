package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

//@Getter //Defines the getter methods for all data members in the class internally,they are not visible in the code;
//@Setter//Defines the setter methods for all data members in the class internally,they are not visible in the code;
//@AllArgsConstructor//Defines All argument constructor 
//@NoArgsConstructor//Defines No argument constructor
@Entity
@Table(name="User")

public class User implements Serializable,Comparable<User>
{
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	Integer userId;
	@Column(name = "user_name")
	String userName;
	@Column(name = "password")
	String password;
	String email;
	String fname;
	String lname;
	Date dob;
	Date createdDate;
	Date modifiedDate;
	Timestamp lastAccessed;
	
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Timestamp getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(Timestamp lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", lastAccessed=" + lastAccessed + "]";
	}
	@Override
	public int compareTo(User o) {
//		if(o.createdDate !=null)
//		return this.createdDate.compareTo(o.createdDate);
//		return 0;
		return this.userId-o.userId;
	}
	
}
