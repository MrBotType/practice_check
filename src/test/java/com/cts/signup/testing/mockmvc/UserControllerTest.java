package com.cts.signup.testing.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	@Test
	public void testUserDetailsSaveSuccessfullyUsingToken() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"sarbojitsahareja@GMAIL.com\"" + "," + "\"userName\" : \"soumyadee\"" + ","
				+ "\"password\" : \"soumyadee\"}";
		
		for(int i=0;i<100;i++) {
			System.out.print("*");
		}
		System.out.println();
		for(int i=0;i<100;i++) {
			System.out.print("*");
		}
		System.out.println();
	System.out.println(mockMvc.perform(post("/token").content(EMPLOYEE_REQUEST).contentType("text_plain_value"))
		.andExpect(status().isOk()).toString());

		mockMvc.perform(post("/user/add").header("Authorisation", "Token"+mockMvc.perform(post("/token").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk()).andReturn()).content(EMPLOYEE_REQUEST)
				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
		
	}
	
	@Test
	public void testUserDetailsSaveSuccessfully() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"asdfasdf@asdfasdf.com\"" + "," + "\"userName\" : \"soumyadee\"" + ","
				+ "\"password\" : \"soumyadee\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}

	@Test
	public void testPreExistingEmailId() throws Exception {
		// exceptionRule.expect(NestedServletException.class);
		String EMPLOYEE_REQUEST = "{\"email\" : \"a@gmail.com\"" + "," + "\"userName\" : \"soumyadee\"" + ","
				+ "\"password\" : \"soumyadee\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());/* .andExpect(jsonPath("$.signupStatus").value("true")); */

	}

	@Test
	public void testWrongUrl() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"soumyadeep@gmail.com\"" + "," + "\"userName\" : \"soumyadeep\"" + ","
				+ "\"password\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add1").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void testPasswordValidation() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"soumyadeep@gmail.com\"" + "," + "\"userName\" : \"soumyadeep\"" + ","
				+ "\"password\" : \"6\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Password must be 3 to 20 characters"));
	}

	@Test
	public void testUserNameValidation() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"soumyadeep@gmail.com\"" + "," + "\"userName\" : \"s\"" + ","
				+ "\"password\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Name must be 3 to 20 characters"));
		
	}

	@Test
	public void testNullUserName() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"soumyadeep@gmail.com\"" + "," + "\"password\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: User Name cannot be empty"));

	}

	@Test
	public void testNullEmailAddress() throws Exception {

		String EMPLOYEE_REQUEST = "{\"userName\" : \"soumyadeep\"" + "," + "\"password\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Email cannot be empty"));

	}

	@Test
	public void testNullPassword() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"soumyadeep@gmail.com\"" + "," + "\"userName\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Password cannot be empty"));
	}

	@Test
	public void testShortEmailAddress() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"qwer\"" + "," + "\"userName\" : \"soumyadeep\"" + ","
				+ "\"password\" : \"soumyadeep\"}";

		mockMvc.perform(post("/user/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: email must be 8 to 50 characters"));

	}
	
}