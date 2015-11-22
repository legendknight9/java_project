/**
 * 
 */
package nhutlt.struts1.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author nhutlt
 *
 */
public class feedBackAction extends Action {
	private final String query = "Insert into feedback values (?, ?, ?, ?, ?)";
	private final String userName = "root";
	private final String passWord = "rootroot3#";
	private final String databaseName = "test";	
	private final String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String connection = "jdbc:sqlserver://localhost:1433;databaseName=" 
										+ databaseName + ";" + "user=" 
										+ userName + " ;password=" + passWord;
	private final int subjectID = 1;
	private int userID;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward nextPage = null;
		feedBackForm fbf = (feedBackForm) form;
		List<feedback> feedBackData = fbf.getFeedBackData();
		userID = fbf.getUserID();
		
		if (fbf.getClickbutton().equals("Send")) {
			try {
				Class.forName(dbDriver);
				Connection con = DriverManager.getConnection(connection, userName, passWord);
				PreparedStatement pstat = con.prepareStatement(query);
				while(feedBackData.size() > 0) {
					feedback data = feedBackData.remove(0);
					pstat.setInt(1, userID);
					pstat.setInt(2, subjectID);
					pstat.setInt(3, data.getQuestionID());
					pstat.setInt(4, data.getAnswerID());
					pstat.setString(5, data.getNote());
					pstat.executeUpdate();			
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}			
		}
		
		nextPage = mapping.findForward("success");
		return nextPage;
	}
}

