package model.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

class ConnectionUtility {
	private String JDBC_DRIVER;
	private String DATABASE_URL;
	private String USER;
	private String PASSWORD;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	ConnectionUtility() {
		getConnectionParameters();
	}
	
	private void getConnectionParameters() {
		try (FileInputStream fileInputStream = new FileInputStream("D:/IT/Java/Soft/projects/gitIJ/RepositoryGoIT/GoIT_Project_1/src/java_ee_module_2/config.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);
			JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
			DATABASE_URL = properties.getProperty("DATABASE_URL") + properties.getProperty("DB") + properties.getProperty("SCHEMA");
			USER = properties.getProperty("USER");
			PASSWORD = properties.getProperty("PASSWORD");
		} catch (Exception e) {e.printStackTrace();}
	}
	
	Connection getConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
			System.out.println("Hello wergfb;iegv4e!");
		}
		return connection;
	}
	
	void closeConnection() {
		try {
			if(connection != null) connection.close();
		} catch (SQLException e) {e.printStackTrace();}
		try {
			if(preparedStatement != null) preparedStatement.close();
		} catch (SQLException e) {e.printStackTrace();}
		try {
			if(resultSet != null) resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}
	}

	PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	ResultSet getResultSet() {
		return resultSet;
	}

	void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}
