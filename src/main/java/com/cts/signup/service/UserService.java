package com.cts.signup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;

	@Transactional
	public SignupStatus saveUser(User user) {
		LOGGER.info("UserService : START ");
		LOGGER.debug("Incoming User Details :" + user);
		SignupStatus signupStatus = new SignupStatus();
		if (!userDao.getUserByEmailId(user)) {
			userDao.save(user);
			signupStatus.setEmailExist(false);
			signupStatus.setSignupStatus(true);
			signupStatus.setMessage("Sign up successfull!");

		} else {
			LOGGER.info("Email Id already Exists. ");
			signupStatus.setEmailExist(true);
			signupStatus.setSignupStatus(false);
			signupStatus.setMessage("Sign up failed!");
		}
		LOGGER.info("UserService : END ");
		return signupStatus;
	}
}
