package db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPool {

	private static ConnectionPool pool = null;
	private static DataSource dsVideotracking = null;

	private ConnectionPool() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up data source
			dsVideotracking = (DataSource) envCtx.lookup("jdbc/videotracking");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConnectionPool getInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}

	public Connection getVideotrackingConn() {
		try {
			return dsVideotracking.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
