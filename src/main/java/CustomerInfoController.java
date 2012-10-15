import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NM;
import dao.CustomerDAO;
import domain.Customer;

/**
 * Customer info controller. create/update customer object page
 * @author andrey
 *
 */
public class CustomerInfoController extends HttpServlet {
	
	private CustomerDAO customerDAO = new CustomerDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setAttribute("customer", customerDAO.getCustomer( 
					(String) req.getSession().getAttribute(NM.USERNAME)));
		} catch (SQLException e) {
			// TODO logger
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO logger
			System.out.println(e);
		}
		RequestDispatcher view = req.getRequestDispatcher(NM.THIRD_PAGE);
		view.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customer customer = new Customer();
		customer.setUsername((String)req.getSession().getAttribute(NM.USERNAME));
		customer.setName(req.getParameter("name"));
		customer.setSurname(req.getParameter("surname"));
		customer.setSex(req.getParameter("sex"));
		try {
			customerDAO.editOrSaveCustomer(customer);
		} catch (SQLException e) {
			// TODO logger
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO logger
			System.out.println(e);
		}
		doGet(req, resp);
	}

}
