package com.monkeds.dexter_frontend.ws_client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class WSUserClient {

//	@Test
	public void testGetById(){
	    Client client = Client.create();
	    
	    String idUser ="MONKED";

		WebResource webResource = client.resource(getBaseURI()).path("users").path(idUser);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
//	
//	@Test
	public void getByCredentials(){
		String output;
		Client client = Client.create();
	    
		WebResource webResource = client.resource(getBaseURI())
				.path("users")
				.path("credentials")
//				.queryParam("email", "xxx")
//				.queryParam("password", "yyy");
				.queryParam("email", "medient@hotmail.com")
				.queryParam("password", "1234");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		System.out.println("codigo de estado "+response.getStatus());
//		System.out.println("mas "+response.);
		switch(response.getStatus()){
			case 200:{
				
			}
			case 404:{
				
			}case 204:{
				
			}
		}
		
	}
	
	@Test
	public void getByCredentials2(){
		String output;
		Client client = Client.create();
		WebResource webResource = client.resource(getBaseURI())
				.path("users")
				.path("credentials")
				.queryParam("email", "xxx")
				.queryParam("password", "yyy");
//				.queryParam("email", "medient@hotmail.com")
//				.queryParam("password", "1234");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		System.out.println("codigo estatus" +response.getStatus());
		System.out.println("resultado"+response.getEntity(String.class));
	}
	
	
	
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/dexter_backend/").build();
	  }
}
