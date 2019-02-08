package com.cts.signup.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;
import com.cts.signup.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/getAllArticles")
	public SignupStatus registerUser(@RequestBody User user) {
		LOGGER.info("UserController : START ");
		SignupStatus signupStatus = userService.saveUser(user);
		// LOGGER.debug("Incoming User Details :"+user);
		LOGGER.debug("SignupStatus :" + signupStatus);
		LOGGER.info("UserController : END ");
		return signupStatus;
	}
}
