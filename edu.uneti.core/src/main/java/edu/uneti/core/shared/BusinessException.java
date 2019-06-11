package edu.uneti.core.shared;

public class BusinessException extends Exception {
	
	private static final long serialVersionUID = -2556322490055461261L;
	
	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
	
	public BusinessException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
