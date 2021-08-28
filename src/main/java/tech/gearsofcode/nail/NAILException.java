package tech.gearsofcode.nail;

public class NAILException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529605647472296935L;

	public NAILException() {
		super();
	}

	public NAILException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NAILException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NAILException(String arg0) {
		super(arg0);
	}

	public NAILException(Throwable arg0) {
		super(arg0);
	}

}
