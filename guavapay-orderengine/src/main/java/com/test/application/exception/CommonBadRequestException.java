package com.test.application.exception;

import lombok.Getter;

@Getter
public class CommonBadRequestException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer code;
    private final String message;

    public CommonBadRequestException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
