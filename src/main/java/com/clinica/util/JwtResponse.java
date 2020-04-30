package com.clinica.util;

public class JwtResponse {
	private String username;
	private String accessToken;
	private String tokenType = "Bearer";

	public JwtResponse(String accessToken, String username) {
		this.accessToken = accessToken;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccesToken() {
		return accessToken;
	}
	public void setAccesToken(String accesToken) {
		this.accessToken = accesToken;
	}

	public String getTokeType() {
		return tokenType;
	}
	public void setTokeType(String tokeType) {
		this.tokenType = tokeType;
	}
	
}