package com.cts.signup.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;

@Component
public class UserDao {

	private SessionFactory hibernateFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.hibernateFactory = emFactory.unwrap(SessionFactory.class);
	}

	public void save(User user) {
		LOGGER.info("UserDao SAVE method : START ");
		// LOGGER.debug("Incoming User Details :"+user);
		try {
			Session session = hibernateFactory.openSession();
			session.save(user);
			session.close();
			LOGGER.info("\n\n Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			LOGGER.info("error");
		}
		LOGGER.info("UserDao SAVE method : END ");
	}

	public boolean getUserByEmailId(User user) {
		LOGGER.info("UserDao Getting User By Email Id method : START ");
		User preExistingUser = null;
		// LOGGER.debug("Incoming User Details :"+user);
		// try {
		Session session = hibernateFactory.openSession();
		// session.save(user);
		List<User> users = (List<User>) session.createQuery("select user from User user where user.email = :emailid")
				.setParameter("emailid", user.getEmail()).list();
		LOGGER.debug("Pre Existing User List : " + users);
		session.close();
		for (User u : users) {
			preExistingUser = u;
		}
		if (preExistingUser != null) {
			LOGGER.info("\n\n User Details Found By Email Id\n");
			LOGGER.info("UserDao Getting User By Email Id method : END ");
			return true;
		}
		LOGGER.info("\n\n No User Details Found By Email Id\n");
		LOGGER.info("UserDao Getting User By Email Id method : END ");
		return false;
	}
}