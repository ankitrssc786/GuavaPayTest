package com.test.application.exception;

public class OrderCanNotCancelException extends CommonBadRequestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderCanNotCancelException() {
        super(400, "Order is in progress so it can not be cancelled");
    }

}
