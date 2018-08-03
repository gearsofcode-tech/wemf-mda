package com.gearsofcode.wemf;

/**
 * Thrown when a user query syntax is invalid.
 * */
public class WEMFSyntaxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 427839720271037946L;



	public WEMFSyntaxException() {
		super();
	}



	public WEMFSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



	public WEMFSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}



	public WEMFSyntaxException(String message) {
		super(message);
	}



	public WEMFSyntaxException(Throwable cause) {
		super(cause);
	}



}
