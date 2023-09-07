package com.project.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EmployeeServlet, handles initial request and talk to EmployeeUtility and send it tp JSP
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeUtility employeeUtility;
	
	@Resource(name="jdbc/employee_tracker")
	private DataSource src;
	
	@Override
	public void init() throws ServletException {
		super.init();
		//initialize EmbloyeeUtility, pass it in data source object
		try {
			employeeUtility = new EmployeeUtility(src);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list the employees (get data, set attributes, send into JSP)
		try {
			showEmployees(request, response);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	private void showEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//get Employees from database utility
		List<Employee> employees = employeeUtility.getListEmployees();
		
		//add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees); 
		
		//send to JSP page using request dispatcher
		RequestDispatcher disp = request.getRequestDispatcher("/employees-list.jsp");
		disp.forward(request,  response);
		
	}

	

}
