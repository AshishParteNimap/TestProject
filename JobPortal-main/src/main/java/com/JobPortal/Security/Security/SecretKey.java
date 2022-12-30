package com.JobPortal.Security.Security;

public class SecretKey {

	private String secretKey;
	private long  expirationInMilSec;
	public SecretKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SecretKey(String secretKey, long expirationInMilSec) {
		super();
		this.secretKey = secretKey;
		this.expirationInMilSec = expirationInMilSec;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public long getExpirationInMilSec() {
		return expirationInMilSec;
	}
	public void setExpirationInMilSec(long expirationInMilSec) {
		this.expirationInMilSec = expirationInMilSec;
	}
	
	
}
