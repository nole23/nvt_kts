package com.konstrukcija.dto;

public class MessageDTO {

	private String success;
	private String error;
	private String cookies;
	private String rola;
	private Long id;
	
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
