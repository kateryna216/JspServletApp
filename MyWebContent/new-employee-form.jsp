<!DOCTYPE html>

<html>
	<head>
		<title id="title-add">Add New Employee</title>
		
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/new-employee-form-style.css">
	</head>

	<body>
		<div id="containerOut">
			<div id="header">
				<h2>
					Software Corporation
				</h2>
			</div>
		</div>
		
		<div id="containerIn">
		`	<h2>Add New Employee</h2>
			<form action ="EmployeeServlet" method="GET">
				<input type="hidden" name="toDo" value="AddNew" />
				
				<table>
				
					<tbody>
						<tr>
							<td><label>First Name:</label></td>
							<td><input type="text" name="firstName"/></td>
						</tr>
						<tr>
							<td><label>Last Name:</label></td>
							<td><input type="text" name="lastName"/></td>
						</tr>
						<tr>
							<td><label>Email Address:</label></td>
							<td><input type="text" name="emailAddress"/></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Submit" class="submit"/></td>
						</tr>
					</tbody>
				
				</table>
			
			</form>
			
			<div style="clear: both;"></div>
			
			<p> 
				<a href="EmployeeServlet">Go Back</a>
			</p>
				
		</div>
	
	</body>
</html>
