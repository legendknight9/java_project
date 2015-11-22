/**
 * 
 */
package nhutlt.struts1.demo;

/**
 * @author nhutlt
 *
 */
public class feedback {
	private int questionID;
	private int answerID;
	private String note;
	
	public feedback(int questionID, int answerID, String note) {
		this.questionID = questionID;
		this.answerID = answerID;
		this.note = note;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
