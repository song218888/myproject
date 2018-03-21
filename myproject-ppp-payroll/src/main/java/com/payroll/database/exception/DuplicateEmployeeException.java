package com.payroll.database.exception;

public class DuplicateEmployeeException extends EmployeeDatabaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8260596563448045269L;
	
	public DuplicateEmployeeException() {
		super();
	}

	public DuplicateEmployeeException(String message) {
		super(message);
	}
	
}
