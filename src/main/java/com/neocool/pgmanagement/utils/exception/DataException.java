package com.neocool.pgmanagement.utils.exception;

public class DataException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public DataException(String message,Throwable th){
        super(message,th);
    }
}