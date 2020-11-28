package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lilei
 */
public class JdbcUtil {

	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/UserPowerControl?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
	private final String user = "root";
	private final String password = "1234";
	public static JdbcUtil INSTANCE;

	static {
		INSTANCE = new JdbcUtil();
	}

	private JdbcUtil() {	super();	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) { e.printStackTrace(); }
		return conn;
	}


	public void closeConn(Connection conn) {
		try {
			if (conn != null) {  conn.close(); }
		} catch (SQLException e) {  e.printStackTrace();  }
	}

	public static void main(String[] args) {
		System.out.println(JdbcUtil.INSTANCE.getConnection().toString());
	}

}
