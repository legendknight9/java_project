package nhutlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmitServlet
 */
@WebServlet("/SubmitServlet")
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");

		if (username != null && username.trim().length() > 0 
				&& username.contains(" ")) {
			PrintWriter writer = response.getWriter();
			writer.println("<html><title> Welcome</title><body>"
					+ "Welcome " + username + "</body></html>");
		} else {
			response.sendRedirect("/Ass1_Day8/index.html");
		}
	}

}
