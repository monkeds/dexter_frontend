package com.monkeds.dexter_frontend.entity;

public class AjaxResult {

//	private String ajaxCode;
	private String ajaxMessage;
	
	
	
	public AjaxResult(/*String ajaxCode,*/ String ajaxMessage) {
		super();
//		this.ajaxCode = ajaxCode;
		this.ajaxMessage = ajaxMessage;
	}
//	public String getAjaxCode() {
//		return ajaxCode;
//	}
//	public void setAjaxCode(String ajaxCode) {
//		this.ajaxCode = ajaxCode;
//	}
	public String getAjaxMessage() {
		return ajaxMessage;
	}
	public void setAjaxMessage(String ajaxMessage) {
		this.ajaxMessage = ajaxMessage;
	}
	
	
}
