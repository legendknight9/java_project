package nhutlt.jsp.customtag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TraineeListTag extends SimpleTagSupport{
	
	@Override
	public void doTag() throws JspException, IOException {
		String query = "Select * from guest ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			JspWriter out = getJspContext().getOut();
			
			out.println("<br>");
			out.println("The trainee list :" + "<br>");

			while(rs.next()) {
				out.println(rs.getString("lastname") + " " + rs.getString("firstname")  + "<br>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
