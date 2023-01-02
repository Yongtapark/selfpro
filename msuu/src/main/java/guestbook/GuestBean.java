
package guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBean {
	// 데이터베이스 연결관련 변수 선언
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;

	// 데이터베이스 연결관련정보를 문자열로 선언
	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	// 데이터베이스 연결 메서드
	void connect() {
		// JDBC 드라이버 로드
		try {
			Class.forName(jdbc_driver);
			// 데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결 종료 메서드
	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

// -------------------------------------------------------------------------
	// 1. 데이터 갱신을 위한 메서드
	public boolean updateDB(GuestBook guestbook) {
		connect();
		try {
			sql = "insert into GUESTBOOK (GB_NAME, GB_EMAIL, GB_PASSWD,GB_TELL,GB_DATE,GB_CONTENTS) VALUES (?,?,?,?,SYSDATE,?) WHERE GB_ID=?";
			pstmt.setString(1, guestbook.getGB_NAME());
			pstmt.setString(2, guestbook.getGB_EMAIL());
			pstmt.setString(3, guestbook.getGB_TEL());
			pstmt.setString(4, guestbook.getGB_PASSWD());
			pstmt.setString(5, guestbook.getGB_CONTENTS());
			pstmt.setInt(6, guestbook.getGB_ID());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}// updateDB

	// 2. 게시물 삭제를 위한 메서드
	public boolean deleteDB(int gb_id) {
		connect();

		try {
			sql="delete from GUESTBOOK WHERE ID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, gb_id);

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}// deleteDB

	// 3. 게시물 등록 메서드
	public boolean insertDB(GuestBook guestbook) {
		connect();
		sql = "insert into guestbook(GB_NAME,GB_EMAIL,GB_DATE,GB_TEL,GB_PASSWD,GB_CONTENTS) VALUES(?,?,SYSDATE,?,?,?)";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, guestbook.getGB_NAME());
			pstmt.setString(2, guestbook.getGB_EMAIL());
			pstmt.setString(3, guestbook.getGB_TEL());
			pstmt.setString(4, guestbook.getGB_PASSWD());
			pstmt.setString(5, guestbook.getGB_CONTENTS());
			
			
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}// insertDB
		// 4. 게시물 하나의 모든 정보를 가지고 오는 메서드

	public GuestBook getDB(int gb_id) {
		connect();
		GuestBook guestbook = new GuestBook();
		sql = "select * from guestbook where gb_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gb_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			guestbook.setGB_ID(rs.getInt("gb_id"));
			guestbook.setGB_NAME(rs.getString("gb_name"));
			guestbook.setGB_EMAIL(rs.getString("gr_email"));
			guestbook.setGB_DATE(rs.getDate("gb_date"));
			guestbook.setGB_TEL(rs.getString("gb_tel"));
			guestbook.setGB_CONTENTS(rs.getString("gb_contents"));
			guestbook.setGB_PASSWD(rs.getString("gb_passwd"));
			rs.close();

		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			disconnect();
		}

		return guestbook;
	}// getDB
		// 5. 게시물 목록 출력을 위해 전체 게시물을 가지고 오는 메서드

	public ArrayList getDBList() {
		connect();
		ArrayList<GuestBook> datas = new ArrayList<GuestBook>();
		sql = "select * from GUESTBOOK";

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBook guestbook = new GuestBook();
				guestbook.setGB_ID(rs.getInt("gb_id"));
				guestbook.setGB_NAME(rs.getString("gb_name"));
				guestbook.setGB_EMAIL(rs.getString("gb_email"));
				guestbook.setGB_DATE(rs.getDate("gb_date"));
				guestbook.setGB_TEL(rs.getString("gb_tel"));
				guestbook.setGB_CONTENTS(rs.getString("gb_contents"));

				datas.add(guestbook);

			}
			rs.close();
		} catch (Exception e) {
			e.getStackTrace();

		} finally {
			disconnect();
		}

		// 처리가 끝나 ArrayList 를 리턴함.
		return datas;

	}// getDBList
}
