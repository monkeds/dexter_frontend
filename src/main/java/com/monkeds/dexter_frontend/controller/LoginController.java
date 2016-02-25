package com.monkeds.dexter_frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.monkeds.dexter_frontend.service.UserService;

@Controller("loginController")
public class LoginController {
	
	
	UserService userService = new UserService();
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String init(){
		System.out.println("ingreso al metodo inicial");
		return "index";
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public void login(HttpServletRequest request) {
//		System.out.println("entro login "+request.getParameter("email"));
//		System.out.println("entro login "+request.getParameter("password"));
//	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("entro login "+email+" / "+password);
		String user = userService.getByCredentials(email, password);
		System.out.println(user);
	}
	
//	ESTO ES VALIDO
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public void login(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("entro login por HTTP "+request.getParameter("email")+" / "+request.getParameter("password"));
//	}

}
