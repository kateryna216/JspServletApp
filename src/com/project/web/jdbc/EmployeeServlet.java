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
		
		
		
		try {
			//read toDo parameter
			String command = request.getParameter("toDo");
			
			//default to list of employees
			if(command == null) {
				command="EMPLOYEE_LIST";
			}
			//choose needed method
			switch(command) {
			case "EMPLOYEE_LIST":
				showEmployees(request,response);
				break;
				
			case "AddNew":
				newEmployee(request,response);
				break;
				
			default:
				showEmployees(request,response);
			}
			
			
			//show the list of the employees (get data, set attributes, send into JSP)
			showEmployees(request, response);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	private void newEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read employee data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		
		//create new employee object
		Employee employee = new Employee(firstName, lastName, emailAddress);
		//add new employee to database
		employeeUtility.newEmployee(employee);
		
		//send back list of employees
		showEmployees(request, response);
	}

	private void showEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//get Employees from database employeeUtility
		List<Employee> employees = employeeUtility.getListEmployees();
		
		//add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees); 
		
		//send to JSP page using get request dispatcher
		RequestDispatcher disp = request.getRequestDispatcher("/employees-list.jsp");
		
		disp.forward(request, response);
		
		return;
		
		
	}

	

}
