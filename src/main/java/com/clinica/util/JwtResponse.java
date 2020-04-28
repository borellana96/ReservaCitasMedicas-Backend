package com.clinica.util;

public class JwtResponse {
	private String username;
	private String accesToken;
	private String tokeType = "Bearer";

	public JwtResponse(String accessToken, String username) {
		this.accesToken = accessToken;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccesToken() {
		return accesToken;
	}
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public String getTokeType() {
		return tokeType;
	}
	public void setTokeType(String tokeType) {
		this.tokeType = tokeType;
	}
	
}