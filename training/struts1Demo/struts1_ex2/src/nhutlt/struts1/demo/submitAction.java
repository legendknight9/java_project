/**
 * 
 */
package nhutlt.struts1.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author nhutlt
 *
 */
public class submitAction extends Action {
	private final String query = "Select * from trainee";
	private final String userName = "root";
	private final String passWord = "rootroot3#";
	private final String databaseName = "test";	
	private final String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=" 
										+ databaseName + ";" + "user=" 
										+ userName + " ;password=" + passWord;
	private final String columnFirstName = "First Name";
	private final String columnLastName = "Last Name";
	private final String columnEmail = "Email";
	private final String columnID = "traineeID";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward nextPage = null;
        if (isCancelled(request)) {
            return mapping.findForward("firstpage");
        }
        
        submitForm inputForm = (submitForm) form;
        if (inputForm.getClickbutton().equals("Submit")) {
	        List<trainee> list = new ArrayList<trainee>();
			String queryInsert = "Insert into trainee values ('" + inputForm.getFirstName()
					+ "', '" + inputForm.getLastName() 
					+ "', '" + inputForm.getEmail() +  "')";			
			try {
				Class.forName(dbDriver);
				Connection con = DriverManager.getConnection(connection, userName, passWord);
				Statement stat = con.createStatement();
				if(stat.executeUpdate(queryInsert) == 0) {
					nextPage = mapping.findForward("error");
					return nextPage;
				}
				ResultSet rs = stat.executeQuery(query);
				int currentUserID = 0;
				while(rs.next()) {		
					trainee data = new trainee(rs.getString(columnFirstName), 
							rs.getString(columnLastName), rs.getString(columnEmail));
					currentUserID = rs.getInt(columnID);
					list.add(data);
				}			
				request.getSession().setAttribute("userID", currentUserID);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("ListTrainee", list);
			nextPage = mapping.findForward("success");
        }
		return nextPage;
	}

}

