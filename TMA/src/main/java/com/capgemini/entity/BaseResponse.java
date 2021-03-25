package com.capgemini.entity;

/**
 * The Class BaseResponse.
 */
public class BaseResponse {

	private Object response;
	private int statusCode;

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response the new response
	 */
	public void setResponse(Object response) {
		this.response = response;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}