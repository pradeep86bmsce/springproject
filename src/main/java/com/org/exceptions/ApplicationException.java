/**
 * 
 */
package com.org.exceptions;

/**
 * @author M1030876
 *
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message exception message
	 * @param exception base exception
	 */
	public ApplicationException(String message, Throwable exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ApplicationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ApplicationException(Throwable ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationException [getMessage()=" + getMessage()
				+ ", getClass()=" + getClass() + "]";
	}
	
	

	
}
