
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Employee Tracker Application
		</title>
		
		<link type="text/css" rel="stylesheet" href="css/style.css">
		
	</head>
	
	
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
						<th>Operation</th>
					</tr>
					
					<!-- fill Employees' names -->
					<c:forEach var="currentEmployee" items="${EMPLOYEE_LIST}"> 
					
					<!-- link to remove employee -->
						<c:url var="removeLink" value="EmployeeServlet">
							<c:param name="toDo" value="REMOVE" />
							<c:param name="employeeId" value="${currentEmployee.employeeId}" /> 
						</c:url>
					
						<tr>
							<td> ${currentEmployee.firstName}</td>	
							<td> ${currentEmployee.lastName}</td>
							<td> ${currentEmployee.emailAddress}></td>
							<td> <a href="${removeLink}" onclick="if (!(confirm('Remove the employee?'))) return false">Remove</a></td>
						</tr>
					
					</c:forEach>
				</table>
			</div>
			
			<br /> <br />
			
			<!--  button Add Employee -->
			<input type="button" value="Add New Employee" onclick="window.location.href='new-employee-form.jsp'; return false;" class="new-employee"/>
			
			
				
			
		</div>
		
		
	</body>
	
	
</html>
