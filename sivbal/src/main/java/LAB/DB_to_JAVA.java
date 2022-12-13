package LAB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DB_to_JAVA {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select studno,name,userid,tel from student ");//��Ÿ������ 
		
		// like '%' || ? || '%' ");
		//|| => ���ڿ� ����, �������� (���� or ����) && => �Է� => AND(�ڹ��� Scanner ���)
		
		try {
			conn=ConUtill.getConnection();
			pstmt=conn.prepareStatement(sql.toString());
			
			//pstmt.setInt(1, 101);//����ǥ�� ���� ä��, �������� setInt
			//������ �б�
			rs=pstmt.executeQuery();//rs�� ������ ����
			
			
			//while ����
			while(rs.next()) {//next�� Ŀ���� �̵��� ������ �ϳ��� ����
			System.out.print(rs.getString("name")+"\t");
			System.out.print(rs.getInt("studno")+"\t");	
			System.out.print(rs.getString("userid")+"\t");	
			System.out.println(rs.getString("tel"));	
			}
			
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}