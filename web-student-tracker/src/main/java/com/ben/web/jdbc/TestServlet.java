package com.ben.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	Define datasource/connection for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Set up the PrintWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
//		Get a connection to database
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		try { 
			myConnection = dataSource.getConnection();
			
			
//		Create SQL statements
			String sql = "select * from student";
			myStatement = myConnection.createStatement();
			
//		Execute SQL query
			myResultSet = myStatement.executeQuery(sql);
			
//		Process the result set
			while(myResultSet.next()) {
				String firstName = myResultSet.getString("FIRST_NAME");
				out.println(firstName);
			}
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}




}
