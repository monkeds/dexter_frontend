package com.monkeds.dexter_frontend.service;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserService {

	
	public String getByCredentials(String email,String password){
		String output;
		Client client = Client.create();
		WebResource webResource = client.resource(getBaseURI())
				.path("users")
				.path("credentials")
				.queryParam("email", email)
				.queryParam("password", password);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		return response.getEntity(String.class);
	}
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/dexter_backend/").build();
	}
}
