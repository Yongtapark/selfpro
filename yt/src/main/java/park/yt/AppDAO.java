package park.yt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppDAO {
	public AppVO getEmployeeregiste(AppVO evo) throws Exception {
		// 2. 데이터 처리를 위한 SQL 문
		String dml = "insert into App " + " (name)" + "  values " + " (?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		AppVO retval = null;

		try {
			// 3. DBUtil이라는 클래스의 getConnection( )메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4. 입력받은 사용자 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, evo.getName());

			// 5. SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			retval = new AppVO();
			System.out.println("i= " + i);

		} catch (SQLException e) {
			System.out.println("getEmployeeregiste e=[" + e + "]");
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
			}
		}

		System.out.println("입력완료: " + retval.toString());
		return retval;
	}
}
