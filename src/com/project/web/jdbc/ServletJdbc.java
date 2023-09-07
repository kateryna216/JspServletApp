package com.project.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Statement;

/**
 * Servlet implementation class ServletJdbc
 */
@WebServlet("/ServletJdbc")
public class ServletJdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/employee_tracker")
	private DataSource src;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Set up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//get a connection to the database
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		try {
			connection = src.getConnection();
			//create a SQL statements
			String sqlStatement = "select * from  employee";
			statement = connection.createStatement();
			
			//execute SQL query
			result = statement.executeQuery(sqlStatement);
			
			//process the result set
			while (result.next()) { //while I have rows to process
				String email_address = result.getString("email");
				out.println(email_address);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}



