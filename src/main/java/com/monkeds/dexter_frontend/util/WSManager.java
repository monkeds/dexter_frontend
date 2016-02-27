package com.monkeds.dexter_frontend.util;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monkeds.dexter_frontend.constant.WSConstants;
import com.monkeds.dexter_frontend.entity.User;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WSManager {
	
	
	
	
	public static Object getObject(WebResource webResource) throws Exception{
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if(response.getStatus()!=200){
			System.out.println("Error");
			throw new Exception("horror");
		}else{
			JsonParser jsonParser = new JsonParser();
			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
			System.out.println(object.get("code")+" / "+object.get("message"));
			return object.toString();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object getObject(WebResource webResource, Class classType) throws Exception{
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if(response.getStatus()!=200){
			throw new Exception(response.toString());
		}else{
			JsonParser jsonParser = new JsonParser();
			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
			if(object.get("code").getAsInt() ==200){
				return new Gson().fromJson(object, classType);
			}else{
				throw new MkdBackendException(object.get("message").getAsString());
			}
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public static Object postCbject(WebResource webResource, Class classType) throws Exception{
//		ClientResponse response = webResource.accept("application/json").post(ClientResponse.class);
//		if(response.getStatus()!=200){
//			throw new Exception(response.toString());
//		}else{
//			JsonParser jsonParser = new JsonParser();
//			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
//			if(object.get("code").getAsInt() ==200){
//				return new Gson().fromJson(object, classType);
//			}else{
//				throw new MkdBackendException(object.get("message").getAsString());
//			}
//		}
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	public static Object putObject(WebResource webResource, Class classType) throws Exception{
//		ClientResponse response = webResource.accept("application/json").put(ClientResponse.class);
//		if(response.getStatus()!=200){
//			throw new Exception(response.toString());
//		}else{
//			JsonParser jsonParser = new JsonParser();
//			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
//			System.out.println(object.get("code")+" / "+object.get("message"));
//			if(object.get("code").getAsInt() ==200){
//				return new Gson().fromJson(object, classType);
//			}else{
//				throw new MkdBackendException(object.get("message").getAsString());
//			}
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static Object deleteObject(WebResource webResource, Class classType) throws Exception{
//		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
//		if(response.getStatus()!=200){
//			throw new Exception(response.toString());
//		}else{
//			JsonParser jsonParser = new JsonParser();
//			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
//			System.out.println(object.get("code")+" / "+object.get("message"));
//			if(object.get("code").getAsInt() ==200){
//				return new Gson().fromJson(object, classType);
//			}else{
//				throw new MkdBackendException(object.get("message").getAsString());
//			}
//		}
//	}

}
