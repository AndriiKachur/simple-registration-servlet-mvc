import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NM;

/**
 * Controller to show send page, status of logged user
 * @author andrey
 *
 */
public class RegisterController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		showPage(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		doGet(req, resp);
	}
	
	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher view = req.getRequestDispatcher(NM.SECOND_PAGE);
		view.forward(req, resp);
	}

}
