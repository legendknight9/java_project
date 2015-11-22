/**
 * 
 */
package nhutlt.struts2.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;


import nhutlt.struts2.demo.trainee;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author nhutlt
 *
 */
public class submit extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4508915847130829697L;

	private String firstName, lastName, email, account;
	
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
	private List<trainee> list;
	private String emailFormat = "(\\w{1,})@(\\w{1,}).(\\w{3})(.\\w{2})?";
	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<trainee> getList() {
		return list;
	}

	public void setList(List<trainee> list) {
		this.list = list;
	}
	
	public submit() {
		list = new ArrayList<trainee>();
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(connection, userName,
					passWord);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while (rs.next()) {
				trainee data = new trainee(rs.getString(columnID), rs.getString(columnFirstName),
						rs.getString(columnLastName), rs.getString(columnEmail));
				list.add(data);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {
		String queryInsert = "Insert into trainee values ('" + getAccount()
				+ "', '" + getFirstName() + "', '" + getLastName() 
				+ "', '" + getEmail() +  "')";
		trainee currentUser = new trainee(getAccount(), getFirstName(), getLastName(), getEmail());
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(connection, userName, passWord);
			Statement stat = con.createStatement();
			if(stat.executeUpdate(queryInsert) == 0) {
				addActionError("Register unsuccessfully");
				return ERROR;
			} else {
				list.add(currentUser);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}
		ActionContext.getContext().getSession().put("user", currentUser);
		ActionContext.getContext().getSession().put("listuser", list);
		
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (account == null || account.trim().length() == 0
				|| account.trim().length() > 50) {
			addFieldError("account",
					"Account must not be empty or longer than 50 characters");
		} else {
			for (trainee user : list) {
				if (user.getAccount().equals(account)) {
					addFieldError("account",
							"Account is not valid because it is duplicated");
					break;
				}
			}
		}

		if (firstName == null || firstName.trim().length() == 0) {
			addFieldError("firstName", "First Name must not be empty");
		}

		if (lastName == null || lastName.trim().length() == 0) {
			addFieldError("lastName", "First Name must not be empty");
		}

		if (email == null || email.trim().length() == 0) {
			addFieldError("email", "Email must not be empty");
		} else if (!email.matches(emailFormat)) {
			addFieldError("email", "Email is not valid");
		}
	}
}
