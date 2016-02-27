package com.monkeds.dexter_frontend.ws_client;

import org.junit.Before;
import org.junit.Test;

import com.monkeds.dexter_frontend.entity.User;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.monkeds.dexter_frontend.service.UserService;


public class WSUserClient {
	
	
	private UserService userService;
	
	@Before
	public void init(){
		userService = new UserService();
	}

//	@Test
	public void getByCredentials(){
		String message = "";
		 try {
			User user = userService.getByCredentials("medient@hotmail.com", "1234");
			message = user.getNick();
		} catch (MkdBackendException e) {
			message = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			message= "Ocurrio un error al intentar establecer la \n"
					+ "conexion con el servidor. Por favor, intente mas tarde";
		} 

		System.out.println(message);
	}
	
	
	@Test
	public void insert(){
		String message = "";
		 try {
			 User user = new User("vero", "vero1@hotmail.com", "1234", null);
			 message = userService.insert(user);
		} catch (MkdBackendException e) {
			message = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			message= "Ocurrio un error al intentar establecer la \n"
					+ "conexion con el servidor. Por favor, intente mas tarde";
		} 
		System.out.println(message);
	}
	
	
	
}
