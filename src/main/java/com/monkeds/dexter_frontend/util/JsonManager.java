package com.monkeds.dexter_frontend.util;

import com.google.gson.Gson;

public class JsonManager {

	
	public static String toJson(Object object){
		return new Gson().toJson(object);
	}
	
	public static Object toObject(String json, Class classType){
		return new Gson().fromJson(json, classType);
	}
}
