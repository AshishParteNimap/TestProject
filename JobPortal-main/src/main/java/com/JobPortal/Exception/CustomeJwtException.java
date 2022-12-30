package com.JobPortal.Exception;

import org.springframework.http.HttpStatus;

public class CustomeJwtException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus httpStatus;
	public CustomeJwtException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public CustomeJwtException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomeJwtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public CustomeJwtException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CustomeJwtException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CustomeJwtException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
