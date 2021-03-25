package com.capgemini.exception;

/**
 * The Class ResourceNotFound.
 */
public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new resource not found.
	 *
	 * @param message the message
	 */
	public ResourceNotFound(String message) {
		super(message);
	}
}