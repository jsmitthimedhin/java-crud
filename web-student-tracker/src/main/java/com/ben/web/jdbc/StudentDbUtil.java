package com.ben.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
//		get a connection 
		try {
			connection = dataSource.getConnection();
			
//		create sql statement
			String sql = "select * from student order by last_name";
			statement = connection.createStatement();
//		execute the query
			resultSet = statement.executeQuery(sql);
//		process the result set
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("FIRST_NAME");
				String lastName = resultSet.getString("LAST_NAME");
				String email = resultSet.getString("email");
				Student newStudent = new Student(id, firstName, lastName, email);
				students.add(newStudent);
			}
			return students;
//		close JDBC project
		}
		finally {
			close(connection, statement, resultSet);
		}
	}

	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(connection != null) {
				connection.close();
			}
			if(statement != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "INSERT INTO STUDENT "
						+ "(FIRST_NAME, LAST_NAME, EMAIL) "
						+ "VALUES (?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getEmail());
			
//			execute sql insert
			statement.execute();
			
		} finally {
			close(connection, statement, null);
		}
	}

	public Student getStudent(String stringStudentId) throws Exception {
		Student student = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int studentId;
		
		try {
			studentId = Integer.parseInt(stringStudentId);
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM student WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String firstName = resultSet.getString("FIRST_NAME");
				String lastName = resultSet.getString("LAST_NAME");
				String email = resultSet.getString("EMAIL");
				
				student = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student ID: " + studentId);
			}
			
			return student;
			
		}
		finally {
			close(connection, statement, resultSet);
		}
	}

	public void updateStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "UPDATE student "
						+ "SET FIRST_NAME=?, LAST_NAME=?, EMAIL=? "
						+ "WHERE ID=?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getEmail());
			statement.setInt(4, student.getId());
			
			statement.execute();
		}
		finally {
			close(connection, statement, null);
			
		}
	}
	
	public void deleteStudent(int studentId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "DELETE FROM student WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}

}
