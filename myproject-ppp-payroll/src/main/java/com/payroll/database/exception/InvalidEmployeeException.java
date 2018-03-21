package com.payroll.database.exception;

public class InvalidEmployeeException extends EmployeeDatabaseException {

	private static final long serialVersionUID = -7644480328229646173L;

	public InvalidEmployeeException() {
		super();
	}

	public InvalidEmployeeException(String message) {
		super(message);
	}

}
