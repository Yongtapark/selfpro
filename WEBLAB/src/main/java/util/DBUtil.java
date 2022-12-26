/* 
 * - 데이터베이스와 연동을 위한 DBUtil클래스를 작성 
 * getConnect() : Class.forName()을 호촐하여 Oracle의 JDBC드라이버를 등록한다
 * JDBC드라이버를 등록후 DriverManager.getConnect()를 호출하여 데이터메이스에
 * 대한 커넥션을 얻는다.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "oracle.jdbc.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    public static Connection getConnection( ) throws Exception{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "scott", "tiger");
		return con; 
	}
}
