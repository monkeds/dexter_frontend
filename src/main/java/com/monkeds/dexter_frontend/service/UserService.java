package com.monkeds.dexter_frontend.service;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.monkeds.dexter_frontend.constant.WSConstants;
import com.monkeds.dexter_frontend.entity.User;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.monkeds.dexter_frontend.util.WSManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserService {

	
	public User getByCredentials(String email,String password) throws Exception{
		Client client = Client.create();
		WebResource webResource = client.resource(WSConstants.ROOT_URI)
				.path("users")
				.path("credentials")
				.queryParam("email", email)
				.queryParam("password", password);
		return  (User) WSManager.getObject(webResource,User.class);
		
	}
}
