<html>
<head>
	<title>Add Student</title>
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
	<h3>Add Student</h3>
		<form action="StudentControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD"/>
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" required/></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" required /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" required/></td>
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