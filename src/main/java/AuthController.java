import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;
import util.NM;

/**
 * Authentication controller. Show status page and authenticates user.
 * @author andrey
 *
 */
public class AuthController extends HttpServlet {
	
	private CustomerService customerService = new CustomerService(); 
	
	public static void forwardToLogInPage(HttpServletRequest request,
			ServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request
				.getRequestDispatcher(NM.FIRST_PAGE);
		view.forward(request, response);
	}
	
	/**
	 * Authenticated user. Show status.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getSession().getAttribute(NM.AUTH_FLAG) != null){
			RequestDispatcher view = req.getRequestDispatcher(NM.SECOND_PAGE);
			view.forward(req, resp);
			return;
		}		
		resp.sendRedirect("/");		
		
	}
	
	/**
	 * Check xml database and authenticate user.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean authenticated = customerService.checkXmlCredentials(user, password);
		
		if (authenticated){
			req.getSession().setAttribute(NM.AUTH_FLAG, Boolean.TRUE);
			req.getSession().setAttribute(NM.USERNAME, user);
			RequestDispatcher view = req.getRequestDispatcher(NM.SECOND_PAGE);
			view.forward(req, resp);
		} else {
			forwardToLogInPage(req, resp);
		}
		
	}
	
}
