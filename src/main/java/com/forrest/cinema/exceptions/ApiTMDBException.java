/**
 * 
 */
package com.forrest.cinema.exceptions;

/**
 * @author martin
 *
 */
@SuppressWarnings("serial")
public class ApiTMDBException extends Exception {

	/**
	 * 
	 */
	public ApiTMDBException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ApiTMDBException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApiTMDBException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApiTMDBException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApiTMDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
