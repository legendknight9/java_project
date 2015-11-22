/**
 * 
 */
package nhutlt.struts2.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author nhutlt
 *
 */
public class display extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9101082701746213990L;
	private String firstName, lastName, email, account;
	private List<trainee> list;
	private trainee user;
	private List<option> answer;
	
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
	
	public List<option> getAnswer() {
		return answer;
	}

	public void setAnswer(List<option> answer) {
		this.answer = answer;
	}
	
	
	@Override
	public String execute() throws Exception {
//		setFieldErrors((Map) ActionContext.getContext().get("error"));
		Map session = ActionContext.getContext().getSession();
		user = (trainee) session.get("user");
		list = (List<trainee>) session.get("listuser");		
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setAccount(user.getAccount());
		answer = new ArrayList<option>();
		answer.add(new option(1, "Strongly disagree (1)"));
		answer.add(new option(2, "Disagree (2)"));
		answer.add(new option(3, "Neutral (3)"));
		answer.add(new option(4, "Agree (4)"));
		answer.add(new option(5, "Strongly agree  (5)"));
//		String[] currentUser = (String[]) getTexts("currentuser").getObject("listUser")
//		list = (List<trainee>) getTexts("traineelist").getObject("listUser");
//		if (list != null) {
//			return SUCCESS;
//		} else {
//			return ERROR;
//		}
		return SUCCESS;
	}
	
}
