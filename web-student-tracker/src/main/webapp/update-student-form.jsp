<html>
<head>
	<title>Update Student</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Anon University</h2>
		</div>
	</div>
	
	<div id="container">
	<h3>Update Student</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE"/>
			
			<input type="hidden" name="studentId" value="${STUDENT.id}"/>
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" value="${STUDENT.firstName}" required /></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" value="${STUDENT.lastName}" required/></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${STUDENT.email}" required/></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="Submit" class="save" />
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>
</html>