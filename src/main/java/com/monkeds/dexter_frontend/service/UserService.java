package com.monkeds.dexter_frontend.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

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
import com.sun.jersey.api.representation.Form;

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
	
	
	public String insert(User user) throws Exception{
		Client client = Client.create();
		WebResource webResource = client.resource(WSConstants.ROOT_URI).path("users");
		return (String) WSManager.postObject(webResource, user);
//		
//		
//		
//		Client client = Client.create();
//		
//		WebResource webResource = client.resource(WSConstants.ROOT_URI).path("users");
//		Map<String,Object> postBody = new HashMap<String,Object>();
//		postBody.put("nick", nick);
//		postBody.put("email", email);
//		postBody.put("password", password);
//		postBody.put("state", state);
//		
//		Form form = new Form();
//		form.add("nick", nick);
//		form.add("email", email);
//		form.add("password", password);
//		form.add("state", state);
//		
//		ClientResponse response = webResource.post(ClientResponse.class,form);
//		
//		
//		
//		if(response.getStatus()!=200){
//			throw new Exception(response.toString());
//		}else{
//			System.out.println("Error mensaje: "+response.getStatus()+" / "+response.toString());
//			JsonParser jsonParser = new JsonParser();
//			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
//			if(object.get("code").getAsInt() ==200){
//				return object.get("message").getAsString();
//			}else{
//				throw new MkdBackendException(object.get("message").getAsString());
//			}
//		}
	}
}
