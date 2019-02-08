package com.cts.signup.bean;

public class SignupStatus {
	private boolean signupStatus;
	private boolean emailExist;
	private String message;

	public SignupStatus(boolean signupStatus, boolean emailExist, String message) {
		super();
		this.signupStatus = signupStatus;
		this.emailExist = emailExist;
		this.message = message;
	}

	public SignupStatus() {
		super();
	}

	public boolean isSignupStatus() {
		return signupStatus;
	}

	public void setSignupStatus(boolean signupStatus) {
		this.signupStatus = signupStatus;
	}

	public boolean isEmailExist() {
		return emailExist;
	}

	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*@Override
	public String toString() {
		return "SignupStatus [signupStatus=" + signupStatus + ", emailExist=" + emailExist + ", message=" + message
				+ "]";
	}*/

}
