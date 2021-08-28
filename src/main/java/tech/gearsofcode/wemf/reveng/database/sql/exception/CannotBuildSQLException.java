package tech.gearsofcode.wemf.reveng.database.sql.exception;

/**
 * Thrown when an error occurs during SQL generation.
 * 
 * @author Carlos Padoa
 * */
public class CannotBuildSQLException extends Exception {

	private static final long serialVersionUID = -3158988470105852040L;



	public CannotBuildSQLException() {
		super();
	}



	public CannotBuildSQLException(String message, Throwable cause) {
		super(message, cause);
	}



	public CannotBuildSQLException(String message) {
		super(message);
	}



	public CannotBuildSQLException(Throwable cause) {
		super(cause);
	}

}
