/**
 * 
 */
package nhutlt.struts2.demo;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author nhutlt
 *
 */
public class showFeedBack extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2569146389793537146L;

	private List<option> answer;
	
	public List<option> getAnswer() {
		return answer;
	}

	public void setAnswer(List<option> answer) {
		this.answer = answer;
	}
	
	@Override
	public String execute() throws Exception {
//		Map session = ActionContext.getContext().getSession();
//		user = (trainee) session.get("user");
//		list = (List<trainee>) session.get("listuser");		
//		setFirstName(user.getFirstName());
//		setLastName(user.getLastName());
//		setEmail(user.getEmail());
//		setAccount(user.getAccount());
		answer = new ArrayList<option>();
		answer.add(new option(1, "Strongly disagree (1)"));
		answer.add(new option(2, "Disagree (2)"));
		answer.add(new option(3, "Neutral (3)"));
		answer.add(new option(4, "Agree (4)"));
		answer.add(new option(5, "Strongly agree  (5)"));

		return SUCCESS;
	}
}
