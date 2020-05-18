package org.mistry.pms.config;

//create contants for JWT - token validility/signing
public class Constants {
	
	public static final long TOKEN_VALIDITY = 60 * 60 * 2; // 60 seconds * 60 minutes = 3600 * 2 hours = 2h result is in seconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
}
