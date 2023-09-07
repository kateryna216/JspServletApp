package com.project.web.jdbc;

public class Employee {
	
	private int employeeId;
	private String lastName;
	private String firstName;
	private String emailAddress;
	

	
	public Employee() {
		super();
	}

	public Employee(int employeeId, String lastName, String firstName, String emailAddress) {
		super();
		this.employeeId = employeeId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}
	
	
	public Employee(String lastName, String firstName, String emailAddress) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", emailAddress=" + emailAddress + "]";
	}
	
	
	


	

}
