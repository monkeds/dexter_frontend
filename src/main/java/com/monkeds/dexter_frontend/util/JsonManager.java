package com.monkeds.dexter_frontend.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonManager {

	
	public static String toJson(Object object){
		return new Gson().toJson(object);
	}
	
	public static JsonObject stringToJsonObject(String input){
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse( input).getAsJsonObject();
	}
	
	public static Object jsonObjectToObject(JsonObject jsonObject,Class classType){
		return  new Gson().fromJson(jsonObject, classType);
	}
	
//	public static Object toObject(String json, Class classType){
//		return new Gson().fromJson(json, classType);
//	}
}
