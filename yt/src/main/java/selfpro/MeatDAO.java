package selfpro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeatDAO {

	public MeatVO getCrawl(MeatVO evo) throws Exception {

		String dml = "insert into Meat values(?,?,sysdate)";
		String USER_CAM;
		Connection con = null;
		PreparedStatement pstmt = null;
		MeatVO retval = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, evo.getKor_cow());
			pstmt.setString(2, evo.getCow());
			pstmt.setString(3, evo.getPig());
			pstmt.setString(4, evo.getChick());
			pstmt.setString(5, evo.getEgg());

			int i = pstmt.executeUpdate();
			retval = new MeatVO();
			System.out.println("i = " + i);

		} catch (SQLException e) {
			System.out.println("getCrawl e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				// 6. �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		System.out.println("�Է¿Ϸ�: " + retval.toString());
		return retval;
	}

}