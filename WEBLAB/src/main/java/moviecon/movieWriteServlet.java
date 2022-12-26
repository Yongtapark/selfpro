package moviecon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import MovieB.MovieDAO;
import MovieB.MovieVO;


@WebServlet("/movieWrite.do")
public class movieWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("movie/movieWrite.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context=getServletContext();
		String path=context.getRealPath("upload");
		String enctype="UTF-8";
		int sizeLimit=20*1024*1024;
		
		MultipartRequest multi =new MultipartRequest(request, path, sizeLimit, enctype, new DefaultFileRenamePolicy());
		
		String title=multi.getParameter("title");
		int price= Integer.parseInt(multi.getParameter("price"));
		String actor=multi.getParameter("actor");
		String poster=multi.getFilesystemName("poster");
		String director=multi.getParameter("director");
		
		MovieVO mVo=new MovieVO();
		mVo.setTitle(title);
		mVo.setPrice(price);
		mVo.setActor(actor);
		mVo.setDirector(director);
		mVo.setPoster(poster);
		
		MovieDAO mDao= MovieDAO.getInstance();
		mDao.insertMovie(mVo);
		
		response.sendRedirect("movieList.do");
	}

}
