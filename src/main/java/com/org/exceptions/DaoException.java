/**
 * 
 */
package com.org.exceptions;

/**
 * @author M1030876
 *
 */
public class DaoException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param message
	 * @param exception
	 */
	public DaoException(String message, Throwable exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param msg
	 */
	public DaoException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param ex
	 */
	public DaoException(Throwable ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DaoException [getClass()=" + getClass() + ", getMessage()="
				+ getMessage() + "]";
	}


}
