package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.NM.AUTH_FLAG;

/**
 * Authentication filter. Show login page if user is not authenticated, 
 * do authentication, logout or pass request
 * 
 * @author andrey
 * 
 */
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if ( "/logout/".equals(req.getServletPath()) ){
			req.getSession().invalidate();
			String redirect = "".equals(req.getContextPath()) ? 
					req.getScheme() + "://" + req.getLocalName() + ":" + req.getLocalPort() : req.getContextPath();
			((HttpServletResponse) response).sendRedirect(redirect);
			return;			
		}
		if (req.getSession().getAttribute( AUTH_FLAG) == null
				&& !("POST".equals(req.getMethod()) && "/".equals(req.getServletPath()) )) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			view.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
