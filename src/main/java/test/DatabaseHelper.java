package test;

import org.apache.ibatis.jdbc.ScriptRunner;
import util.ConnectionPool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHelper {
	static Connection conn;
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		ConnectionPool.setTest(true);
		conn = ConnectionPool.getConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("..\\SQL\\popolamentoDBTest.sql"));
		sr.runScript(reader);
		
		//DriverManagerConnectionPool.releaseConnection(conn);
		conn.close();
	}
}
