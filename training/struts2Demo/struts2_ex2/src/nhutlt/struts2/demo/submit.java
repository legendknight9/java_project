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
import java.util.List;


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

	private int question1;
	private String note1;
	private int question2;
	private String note2;
	private int question3;
	private String note3;
	private int question4;
	private String note4;
	private int question5;
	private String note5;
	private int question6;
	private String note6;
	private int question7;
	private String note7;
	private int question8;
	private String note8;

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}

	public String getNote6() {
		return note6;
	}

	public void setNote6(String note6) {
		this.note6 = note6;
	}

	public String getNote7() {
		return note7;
	}

	public void setNote7(String note7) {
		this.note7 = note7;
	}

	public String getNote8() {
		return note8;
	}

	public void setNote8(String note8) {
		this.note8 = note8;
	}

	public int getQuestion1() {
		return question1;
	}

	public void setQuestion1(int question1) {
		this.question1 = question1;
	}

	public int getQuestion2() {
		return question2;
	}

	public void setQuestion2(int question2) {
		this.question2 = question2;
	}

	public int getQuestion3() {
		return question3;
	}

	public void setQuestion3(int question3) {
		this.question3 = question3;
	}

	public int getQuestion4() {
		return question4;
	}

	public void setQuestion4(int question4) {
		this.question4 = question4;
	}

	public int getQuestion5() {
		return question5;
	}

	public void setQuestion5(int question5) {
		this.question5 = question5;
	}

	public int getQuestion6() {
		return question6;
	}

	public void setQuestion6(int question6) {
		this.question6 = question6;
	}

	public int getQuestion7() {
		return question7;
	}

	public void setQuestion7(int question7) {
		this.question7 = question7;
	}

	public int getQuestion8() {
		return question8;
	}

	public void setQuestion8(int question8) {
		this.question8 = question8;
	}
	
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
//	private List<option> answer;
	
	public submit() {
		list = new ArrayList<trainee>();
//		answer = new ArrayList<option>();
//		answer.add(new option(1, "Strongly disagree (1)"));
//		answer.add(new option(2, "Disagree (2)"));
//		answer.add(new option(3, "Neutral (3)"));
//		answer.add(new option(4, "Agree (4)"));
//		answer.add(new option(5, "Strongly agree  (5)"));
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

	public List<trainee> getList() {
		return list;
	}

	public void setList(List<trainee> list) {
		this.list = list;
	}

//	public List<option> getAnswer() {
//		return answer;
//	}
//
//	public void setAnswer(List<option> answer) {
//		this.answer = answer;
//	}
}
