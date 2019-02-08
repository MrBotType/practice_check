package com.cts.signup.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "us_id", "us_name", "us_password", "us_email" }) })

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private Integer id;

	@NotNull(message = "User Name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be 3 to 20 characters")

	@Column(name = "us_name")
	private String userName;

	@NotNull(message = "Password cannot be empty")
	@Size(min = 3, max = 20, message = "Password must be 3 to 20 characters")
	@Column(name = "us_password")
	private String password;

	@NotNull(message = "Email cannot be empty")
	@Size(min = 8, max = 50, message = "email must be 8 to 50 characters")
	@Column(name = "us_email")
	private String email;

	public User() {
		super();
	}

	public User( String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}

}
