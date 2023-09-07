<%@ page import="java.util.*, com.project.web.jdbc.*" %>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Employee Tracker Application
		</title>
	</head>
	
	
	<%
		//get employees from request object (sent by servlet)
		List<Employee> employees = (List<Employee>) request.getAttribute("EMPLOYEE_LIST");
	%>
	
	
	
	<body>
	
	 <!-- build HTML table -->
		
		<div id="containerOut">
			<div id="header">
				<h2>
					Software Corporation Employees
				</h2>
			</div>
		</div>
		
		<div id="containerIn">
			<div id="constituents">
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email Address</th>
					</tr>
					
					<!-- fill Employees' names -->
					<% for (Employee current : employees) { %>
					
						<tr>
							<td><%= current.getFirstName() %></td>
								
							<td><%= current.getLastName() %></td>
							<td><%= current.getEmailAddress() %></td>
						
						</tr>
					
					
					<% }%>
				</table>
			</div>
		</div>
	</body>
</html>
