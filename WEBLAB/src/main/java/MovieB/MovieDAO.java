package MovieB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class MovieDAO {

	private MovieDAO() {
		
	}
	
	private static MovieDAO instance=new MovieDAO();
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public List<MovieVO> selectAllMovies(){
		String sql="select * from MOVIE order by code desc";
		List<MovieVO> list = new ArrayList<MovieVO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn =DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MovieVO mVo =new MovieVO();
				mVo.setCode(rs.getInt("code"));
				mVo.setTitle(rs.getString("title"));
				mVo.setPrice(rs.getInt("price"));
				mVo.setActor(rs.getString("actor"));
				mVo.setDirector(rs.getString("director"));
				mVo.setPoster(rs.getString("poster"));
				mVo.setSynopsis(rs.getString("synopsis"));
				list.add(mVo);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt , rs);
		}
		return list;
	}
	
	public void insertMovie(MovieVO mVo){
		String sql="insert into MOVIE values(movie_seq.nextval,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn =DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, mVo.getTitle());
			pstmt.setInt(2, mVo.getPrice());
			pstmt.setString(3, mVo.getDirector());
			pstmt.setString(4, mVo.getActor());
			pstmt.setString(5, mVo.getPoster());
			pstmt.setString(6, mVo.getSynopsis());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteProduct(String code) {
		String sql="delete MOVIE where code=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn =DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public MovieVO selectMovieByCode(String code) {
		String sql="select * from movie where code=?";
		MovieVO mVo=null;
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					mVo = new MovieVO();
					mVo.setCode(rs.getInt("code"));
					mVo.setTitle(rs.getString("title"));
					mVo.setPrice(rs.getInt("price"));
					mVo.setActor(rs.getString("actor"));
					mVo.setDirector(rs.getString("director"));
					mVo.setPoster(rs.getString("poster"));
					mVo.setSynopsis(rs.getString("synopsis"));
					
				}
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBManager.close(conn, pstmt, rs);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return mVo;
			
		
	}
	
	public void updateMovie(MovieVO mVo) {
		String sql= "update movie set title=?, price=?, actor=?, director=?, poster=?, synopsis=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, mVo.getTitle());
			pstmt.setInt(2, mVo.getPrice());
			pstmt.setString(3, mVo.getActor());
			pstmt.setString(4, mVo.getDirector());
			pstmt.setString(5, mVo.getPoster());
			pstmt.setString(6, mVo.getSynopsis());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	
}
