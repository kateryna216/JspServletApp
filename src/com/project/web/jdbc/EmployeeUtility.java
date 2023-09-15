package com.project.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


//work with database JDBC

public class EmployeeUtility {
	
	//reference to dataSource
	private DataSource src;
	
	public EmployeeUtility(DataSource src) {
		this.src = src;
	}
	
	//returns list of Employees
	public List<Employee> getListEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
		//get database connection
		connection = src.getConnection();
		
		//create sql statement
		String sqlQuery = "select * from employee order by last_Name";
		statement = connection.createStatement();
		
		//execute query statement
		result = statement.executeQuery(sqlQuery);
		
		//process the result set
		while(result.next()) {
			//get data from result set row
			int employeeId = result.getInt("id"); //id is database's column name
			String firstName = result.getString("first_Name");
			String lastName = result.getString("last_Name");
			String emailAddress = result.getString("email");
			
			//create new employee object
			Employee current = new Employee(employeeId, firstName, lastName, emailAddress);
			
			//add this object to the list of employees
			employees.add(current);
		}
					
		return employees;
		}
		
		finally {
			//close jdbc objects
			close(result, connection, statement);
		}	
		
	}

	private void close(ResultSet result, Connection connection, Statement statement) {
		try {
			if(connection != null) {
				connection.close(); //makes the connection available for someone to use
			}
			if(statement != null) {
				statement.close();
			}
			if(result != null) {
				result.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

	//add new employee, use JDBC
	public void newEmployee(Employee employee) throws SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			//get db connection
			connection = src.getConnection();
			
			//create sql for insert
			String sqlQuery = "insert into employee(first_Name, last_Name, email) values (?, ?, ?)";
			statement= connection.prepareStatement(sqlQuery);
			
			//set the parameter values for employee
			statement.setString(1, employee.getFirstName());
			statement.setString(2, employee.getLastName());
			statement.setString(3, employee.getEmailAddress());
			
			//execute sql insert
			statement.execute();
		}
		finally {
			//clean up JDBC object
			close(null, connection, statement);
		}
		
		
		
	}

	public void removeEmployee(String employeeId) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			//convert employee id from string to integer
			int employeeIdInt = Integer.parseInt(employeeId);
			//get connection to db
			connection = src.getConnection();
			//create sql query to remove employee
			String sqlQuery = "delete from employee where id=?";
			//prepare statement
			statement = connection.prepareStatement(sqlQuery);
			//set parameters
			statement.setInt(1, employeeIdInt);
			//execure sql statement
			statement.execute();
			
		}
		finally {
			close(null, connection, statement);
		}
		
	}

}
