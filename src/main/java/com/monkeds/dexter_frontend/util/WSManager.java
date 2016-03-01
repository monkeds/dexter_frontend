package com.monkeds.dexter_frontend.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monkeds.dexter_frontend.exception.MkdBackendException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class WSManager {
	
	
	
	
//	public static Object getObject(WebResource webResource) throws Exception{
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//		if(response.getStatus()!=200){
//			System.out.println("Error");
//			throw new Exception("horror");
//		}else{
//			JsonParser jsonParser = new JsonParser();
//			JsonObject object = jsonParser.parse(response.getEntity(String.class)).getAsJsonObject();
//			System.out.println(object.get("code")+" / "+object.get("message"));
//			return object.toString();
//		}
//	}
	
	
	@SuppressWarnings("unchecked")
	public static Object getObject(WebResource webResource, Class classType) throws Exception{
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if(response.getStatus()!=200){
			throw new Exception(response.toString());
		}else{
			JsonObject jsonObject =  JsonManager.stringToJsonObject(response.getEntity(String.class));
			if(jsonObject.get("code").getAsInt() ==200){
				return  JsonManager.jsonObjectToObject(jsonObject, classType);
			}else{
				throw new MkdBackendException(jsonObject.get("message").getAsString());
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object postObject(WebResource webResource, Object object, Class classType) throws Exception{
		Form form = objetToForm(object);		
		ClientResponse response = webResource.post(ClientResponse.class,form);		
		if(response.getStatus()!=200){
			throw new Exception(response.toString());
		}else{
			JsonObject jsonObject =  JsonManager.stringToJsonObject(response.getEntity(String.class));
			if(jsonObject.get("code").getAsInt() ==200){
				if(classType == String.class){
					return jsonObject.get("message").getAsString();
				}else{
					return JsonManager.jsonObjectToObject(jsonObject, classType);
				}
			}else{
				throw new MkdBackendException(jsonObject.get("message").getAsString());
			}
		}
	}

	
	
	
	
	//UTIL
	@SuppressWarnings("unused")
	private static Form objetToForm(Object object){
		Form form = new Form();
		@SuppressWarnings("unchecked")
		Map<String,Object> mappedObject = new ObjectMapper().convertValue(object,Map.class);
		for (Map.Entry<String, Object> entry : mappedObject.entrySet()){
			form.add(entry.getKey(), entry.getValue());
		}
		return form;
	}
}
