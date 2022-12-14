package LAB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SolDAO {

	public SolVO getCrawl(SolVO evo) throws Exception {

		String dml = "insert into SOLDLOGIN values(?,?,sysdate)";
		String USER_CAM;
		Connection con = null;
		PreparedStatement pstmt = null;
		SolVO retval = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, evo.getUSER_NAME());
			pstmt.setString(2, evo.getUSER_CAM());

			int i = pstmt.executeUpdate();
			retval = new SolVO();
			System.out.println("i = " + i);

		} catch (SQLException e) {
			System.out.println("getCrawl e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				// 6. 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("입력완료: " + retval.toString());
		return retval;
	}

}
