package com.neocool.pgmanagement.utils.exception;

public class SystemException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public SystemException(String message, Throwable th){
        super(message, th);
    }
}

