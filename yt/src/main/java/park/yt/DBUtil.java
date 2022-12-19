/* 
 * - �����ͺ��̽��� ������ ���� DBUtilŬ������ �ۼ� 
 * getConnect() : Class.forName()�� ȣ���Ͽ� Oracle�� JDBC����̹��� ����Ѵ�
 * JDBC����̹��� ����� DriverManager.getConnect()�� ȣ���Ͽ� �����͸��̽���
 * ���� Ŀ�ؼ��� ��´�.
 */
package park.yt;
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
