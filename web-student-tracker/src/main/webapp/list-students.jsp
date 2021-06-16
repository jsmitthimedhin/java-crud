<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<title>Student Tracker App</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Anon University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<form action="add-student-form.jsp">
    			<input type="submit" value="Add Student" class="add-student-button" />
			</form>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="eachStudent" items="${STUDENT_LIST}">
				
					<c:url var="tempLink" value = "StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${eachStudent.id}" />
					</c:url>
					
					<!--  setting up link to delete a student -->
					<c:url var="deleteLink" value = "StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${eachStudent.id}" />
					</c:url>
					<tr>
						<td> ${eachStudent.getFirstName()} </td>
						<td> ${eachStudent.lastName} </td>	
						<td> ${eachStudent.email} </td>
						<td>
							<a href = "${tempLink}">Update</a> |  
							<a onclick="if(!(confirm('Are you sure you want to delete this student?'))) {return false;}" href = "${deleteLink}">Delete</a>
						</td>
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>