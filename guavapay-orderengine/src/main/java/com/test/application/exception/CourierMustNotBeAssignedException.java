package com.test.application.exception;

public class CourierMustNotBeAssignedException extends CommonBadRequestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourierMustNotBeAssignedException() {
        super(400, "Courier must not be assigned to unaccepted order");
    }

}