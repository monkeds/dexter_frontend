package com.monkeds.dexter_frontend.constant;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class WSConstants {

	
	public final static String ROOT_WS= "http://localhost:8080/dexter_backend/";
	public final static URI ROOT_URI = UriBuilder.fromUri("http://localhost:8080/dexter_backend/").build();
	
	public final static Integer GET =1;
	public final static Integer POST =2;
	public final static Integer PUT =3;
	public final static Integer DELETE =4;
}
