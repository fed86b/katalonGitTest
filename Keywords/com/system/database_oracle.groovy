package com.system

import java.sql.*;

import javax.sql.rowset.CachedRowSet
import javax.sql.rowset.RowSetProvider

import com.kms.katalon.core.annotation.Keyword

public class database_oracle {


	private static Connection connection = null;

	/**
	 * Open and return a connection to database
	 * @param dataFile absolute file path
	 * @return an instance of java.sql.Connection
	 */
	@Keyword
	def static connectDB(String dataFile=""){
		//Load driver class for your specific database type
		Class.forName("oracle.jdbc.driver.OracleDriver")
		String connectionString = "jdbc:oracle:thin:@il-kmsora1:1521:orautf8"
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = DriverManager.getConnection(connectionString,"dev_heb","dev_heb")
		return connection
	}

	/**
	 * execute a SQL query on database
	 * @param queryString SQL query string
	 * @return a reference to returned data collection, an instance of java.sql.ResultSet
	 */
	@Keyword
	def static executeQuery(String queryString) {
		Statement stm = connection.createStatement()
		ResultSet rs = stm.executeQuery(queryString)
		return rs
	}

	@Keyword
	def static closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = null
	}

	/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */
	@Keyword
	def static execute(String queryString) {
		Statement stm = connection.createStatement()
		boolean result = stm.execute(queryString)
		return result
	}

	private static CachedRowSet GetData(String query) {
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
			rs.populate(ps.executeQuery());
			return rs;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public def static  execute_Query(String queryString) {
		ArrayList<String> arr = new ArrayList<String>();
		CachedRowSet rs = GetData(queryString);
		while (rs != null && rs.next())
			println (rs.getArray(1))
		return arr;

	}


	//	database_oracle.connectDB()
	//
	//	//FROM Northwind.INFORMATION_SCHEMA.COLUMNS
	//	//WHERE TABLE_NAME = N'Customers'
	//
	//	//SELECT TABLE_NAME
	//	//FROM INFORMATION_SCHEMA.TABLES
	//	//WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG='dbName'
	//
	//	//GO
	//	//SELECT *
	//	//FROM sys.Tables
	//	//GO
	//
	//	def recordset= database_oracle.execute_Query("SELECT * FROM ITEM_TYPE_TAB")
	//
	//def recordset= database_oracle.execute_Query("SELECT * FROM  all_tables where rownum <= 10")

	//while (recordset.next()){
	//	Object type= recordset.getObject("ITM_TYPE")
	//	println ("type: "+ type)
	//}
}
