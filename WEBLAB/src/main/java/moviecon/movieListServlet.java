package moviecon;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MovieB.MovieDAO;
import MovieB.MovieVO;

@WebServlet("/movieList.do")
public class movieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDAO mDao=MovieDAO.getInstance();
		
		List<MovieVO> movieList=mDao.selectAllMovies();
		request.setAttribute("movieList", movieList);
		
		System.out.println(movieList);
		
		RequestDispatcher dispatcher =request
				.getRequestDispatcher("movie/movieList.jsp");
		dispatcher.forward(request, response);
	}
}
