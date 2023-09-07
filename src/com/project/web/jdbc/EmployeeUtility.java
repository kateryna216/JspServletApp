package com.project.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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
		//get a connection
		connection = src.getConnection();
		
		//create sql statement
		String sqlQuery = "select * from employee order by last_name";
		statement = connection.createStatement();
		
		//execute query
		result = statement.executeQuery(sqlQuery);
		
		//process the result set
		while(result.next()) {
			//get data from result set row
			int employeeId = result.getInt("id"); //id is database's column name
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
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

}
