package com.cts.signup.testing.mokito;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cts.signup.bean.SignupStatus;
import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.service.UserService;

public class UserServiceTest {

	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private UserService userservice;
	
	
	
    @Before
	    public void setUp() throws Exception {
	         MockitoAnnotations.initMocks(this);
	    }
    
    @Test
    public void testSaveUserDetails() {    
    	boolean statusFalse=false;
        User user = new User();
        user.setEmail("Soumyadeep@cognizant.com");
        user.setPassword("12345678");
        user.setUserName("Soumyadeep");
        Mockito.when(userDao.getUserByEmailId(user)).thenReturn(statusFalse);
        SignupStatus status=userservice.saveUser(user);
        assertEquals(true, status.isSignupStatus() && !status.isEmailExist());
               
    }
    
    @Test
    public void testDuplicateUserEmail() {    
    	boolean statusTrue=true;
        User user = new User();
        user.setEmail("a@gmail.com");
        user.setPassword("1234567s");
        user.setUserName("Soumyadeep Paul");
        Mockito.when(userDao.getUserByEmailId(user)).thenReturn(statusTrue);
        SignupStatus status=userservice.saveUser(user);
        assertEquals(true, !status.isSignupStatus() && status.isEmailExist());
               
    }
}
