/**
 * 
 */
package nhutlt.struts1.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author nhutlt
 *
 */
public class feedBackForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5499888735454681413L;
	
	private String clickbutton;
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
	private List<feedback> feedBackData;
	private int userID;
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the feedBackData
	 */
	public List<feedback> getFeedBackData() {
		return feedBackData;
	}

	/**
	 * @param feedBackData the feedBackData to set
	 */
	public void setFeedBackData(List<feedback> feedBackData) {
		this.feedBackData = feedBackData;
	}

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

	/**
	 * @return the clickbutton
	 */
	public String getClickbutton() {
		return clickbutton;
	}

	/**
	 * @param clickbutton the clickbutton to set
	 */
	public void setClickbutton(String clickbutton) {
		this.clickbutton = clickbutton;
	}

	public feedBackForm() {
		question1 = 0;
		question2 = 0;
		question3 = 0;
		question4 = 0; 
		question5 = 0;
		question6 = 0;
		question7 = 0;
		question8 = 0;
		feedBackData = new ArrayList<feedback>();
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		Object userID = request.getSession().getAttribute("userID");
		if (userID != null) {
			setUserID((int) userID);
		}
		
		ActionErrors errors = new ActionErrors();
		
		if (question1 == 0) {
			errors.add("question1", new ActionError("listpage.error.question1"));
		}
		if (question2 == 0) {
			errors.add("question2", new ActionError("listpage.error.question2"));
		}
		if (question3 == 0) {
			errors.add("question3", new ActionError("listpage.error.question3"));
		}
		if (question4 == 0) {
			errors.add("question4", new ActionError("listpage.error.question4"));
		}
		if (question5 == 0) {
			errors.add("question5", new ActionError("listpage.error.question5"));
		}
		if (question6 == 0) {
			errors.add("question6", new ActionError("listpage.error.question6"));
		}
		if (question7 == 0) {
			errors.add("question7", new ActionError("listpage.error.question7"));
		}
		if (question8 == 0) {
			errors.add("question8", new ActionError("listpage.error.question8"));
		}
		
		if (errors.size() == 0) {
			feedback fb1 = new feedback(1, question1, note1);
			feedback fb2 = new feedback(2, question2, note2);
			feedback fb3 = new feedback(3, question3, note3);
			feedback fb4 = new feedback(4, question4, note4);
			feedback fb5 = new feedback(5, question5, note5);
			feedback fb6 = new feedback(6, question6, note6);
			feedback fb7 = new feedback(7, question7, note7);
			feedback fb8 = new feedback(8, question8, note8);
			feedBackData.add(fb1);
			feedBackData.add(fb2);
			feedBackData.add(fb3);
			feedBackData.add(fb4);
			feedBackData.add(fb5);
			feedBackData.add(fb6);
			feedBackData.add(fb7);
			feedBackData.add(fb8);
		} 
				
		return errors;
	}

	
}
