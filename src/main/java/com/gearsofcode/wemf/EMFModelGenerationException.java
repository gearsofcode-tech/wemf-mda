package com.gearsofcode.wemf;


/**
 * @author Carlos Padoa
 *
 * Thrown whenever is not possible to generate the EMF model from a ".wemf" file.
 */
public class EMFModelGenerationException extends Exception {

	private static final long serialVersionUID = 964862677712282640L;



	public EMFModelGenerationException() {
	}



	public EMFModelGenerationException(String message) {
		super(message);
	}



	public EMFModelGenerationException(Throwable cause) {
		super(cause);
	}



	public EMFModelGenerationException(String message, Throwable cause) {
		super(message, cause);
	}



	public EMFModelGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}