/**
 * 
 */
package nhutlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author nhutlt
 *
 */
public class filter implements Filter {
	private List<String> allowedPage;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		String refererringURLString = request.getHeader("Referer");
		String[] requestPage = refererringURLString.split("/");
		if (allowedPage.contains((String)requestPage[requestPage.length - 1])) {
			chain.doFilter(req, resp);	
		} else {
			PrintWriter out = resp.getWriter();
			out.println("<html><body>Access denied</body></html>");
		}	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String allow = config.getInitParameter("allowedpage");
		allowedPage = new ArrayList<String>();
	    // Default token set: white space.
	    StringTokenizer tok = new StringTokenizer(allow);
	    while(tok.hasMoreTokens())
	    {
	      String allowedSite = tok.nextToken();
	      allowedPage.add(allowedSite);
//	      System.out.println("Banned " + bannedSite);
	    }

	}
	

}
