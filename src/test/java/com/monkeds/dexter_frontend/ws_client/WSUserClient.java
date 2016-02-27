package com.monkeds.dexter_frontend.ws_client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monkeds.dexter_frontend.constant.WSConstants;
import com.monkeds.dexter_frontend.entity.User;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.monkeds.dexter_frontend.util.WSManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class WSUserClient {

//	@Test
//	public void testGetById(){
//	    Client client = Client.create();
//	    
//	    String idUser ="MONKED";
//
//		WebResource webResource = client.resource(getBaseURI()).path("users").path(idUser);
//
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//		if (response.getStatus() != 200) {
//		   throw new RuntimeException("Failed : HTTP error code : "
//			+ response.getStatus());
//		}
//		
//		System.out.println(response.getEntity(User.class).getNick());
////		
////		String output = response.getEntity(String.class);
////		System.out.println(output);
//	}
////	
////	@Test
//	public void getByCredentials(){
//		String output;
//		Client client = Client.create();
//	    
//		WebResource webResource = client.resource(getBaseURI())
//				.path("users")
//				.path("credentials")
////				.queryParam("email", "xxx")
////				.queryParam("password", "yyy");
//				.queryParam("email", "medient@hotmail.com")
//				.queryParam("password", "1234");
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//		
//		System.out.println("codigo de estado "+response.getStatus());
////		System.out.println("mas "+response.);
//		switch(response.getStatus()){
//			case 200:{
//				
//			}
//			case 404:{
//				
//			}case 204:{
//				
//			}
//		}
//		
//	}
	
	@Test
	public void getByCredentials2(){
		Client client = Client.create();
		WebResource webResource = client.resource(WSConstants.ROOT_URI)
				.path("users")
				.path("credentials")
				.queryParam("email", "medient@hotmail.comss")
				.queryParam("password", "1234");
		try {
			User user = (User) WSManager.getObject(webResource, User.class);
			System.out.println("resultado en objeto: "+user.getNick());
		} catch(MkdBackendException me){
			System.out.println(me.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
