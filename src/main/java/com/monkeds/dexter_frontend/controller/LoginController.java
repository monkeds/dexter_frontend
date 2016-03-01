package com.monkeds.dexter_frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.monkeds.dexter_frontend.entity.AjaxResult;
import com.monkeds.dexter_frontend.entity.User;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.monkeds.dexter_frontend.service.UserService;
import com.monkeds.dexter_frontend.util.JsonManager;

@Controller("loginController")
public class LoginController {
	
	
	UserService userService = new UserService();
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String init(){
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(ModelMap model,@RequestParam("email") String email, @RequestParam("password") String password) {
		String message = "";
		 try {
			User user = userService.getByCredentials(email, password);
			message = user.getNick();
		} catch (MkdBackendException e) {
			message = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			message= "Ocurrio un error al intentar establecer la \n"
					+ "conexion con el servidor. Por favor, intente mas tarde";
		} 

		model.put("message", message);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="ajax/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Object result=null;
		 try {
			result = userService.getByCredentials(email, password);
		} catch (MkdBackendException e) {
			result = new AjaxResult(e.getMessage()); 
		} catch (Exception e) {
			result = new AjaxResult("Ocurrio un error al intentar establecer la \n"
					+ "conexion con el servidor. Por favor, intente mas tarde");
		} 
		return JsonManager.toJson(result);
	}
	
	
	@ResponseBody
	@RequestMapping(value="ajax/signUp", method = RequestMethod.POST, headers="Accept=application/json")
	public String signUp(@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("nick") String nick) {
		Object result = null;
		 try {
			 User user = new User(nick,email,password, null);
			 String val = userService.insert(user);
			 result = new AjaxResult(val); 
		} catch (MkdBackendException e) {
			result = new AjaxResult(e.getMessage()); 
		} catch (Exception e) {
			e.printStackTrace();
			result= new AjaxResult("Ocurrio un error al intentar establecer la \n"
					+ "conexion con el servidor. Por favor, intente mas tarde");
		} 
		return JsonManager.toJson(result);
	}
	
	
//	@ResponseBody
//	@RequestMapping(value="ajax/loginO", method = RequestMethod.POST)
//	public String loginO(@RequestParam("email") String email, @RequestParam("password") String password) {
//		Object result=null;
//		 try {
//			result = userService.getByCredentials(email, password);
//		} catch (MkdBackendException e) {
//			result = new AjaxResult("204", e.getMessage()); 
//		} catch (Exception e) {
//			result = new AjaxResult("204","Ocurrio un error al intentar establecer la \n"
//					+ "conexion con el servidor. Por favor, intente mas tarde");
//		} 
//
//		return new Gson().toJson(result);
//	}
	
	
	
//	ESTO ES VALIDO
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public void login(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("entro login por HTTP "+request.getParameter("email")+" / "+request.getParameter("password"));
//	}

}
