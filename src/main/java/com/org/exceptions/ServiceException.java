/**
 * 
 */
package com.org.exceptions;

/**
 * @author M1030876
 *
 */
public class ServiceException extends ApplicationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param exception
	 */
	public ServiceException(String message, Throwable exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public ServiceException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ex
	 */
	public ServiceException(Throwable ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServiceException [getClass()=" + getClass() + ", getMessage()="
				+ getMessage() + "]";
	}
	

}
