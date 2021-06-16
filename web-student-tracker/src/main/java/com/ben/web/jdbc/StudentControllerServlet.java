package com.ben.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
//		create our student db util... and pass in the connection pool/ datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch(Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	public void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
//		create a new student object
		Student newStudent = new Student(firstName, lastName, email);
		studentDbUtil.addStudent(newStudent);
		
//		send student list back to main page
		 response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			Read command and route the code
			String command = request.getParameter("command");
			if(command == null) {
				command = "LIST";
			}
			switch(command) { 
			case "ADD":
				addStudent(request, response);
				break;
				
			 default:
	                listStudents(request, response);
			
			}
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			Read command and route the code
			String command = request.getParameter("command");
			if(command == null) {
				command = "LIST";
			}
			switch(command) {
			
			case "LIST": 
				listStudents(request,response);
				break;
				
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
			
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			default: 
				listStudents(request, response);
			}
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
//		create new student object based on that form data
		Student newStudent = new Student(id, firstName, lastName, email);
		
//		perform update on the database
		studentDbUtil.updateStudent(newStudent);
		
//		send user back to listStudents page
		listStudents(request,response);
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		Student student = studentDbUtil.getStudent(studentId);
		request.setAttribute("STUDENT", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("studentId"));
		studentDbUtil.deleteStudent(id);
		
		listStudents(request, response);
	}

	public void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Student >students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}
	
	



}
