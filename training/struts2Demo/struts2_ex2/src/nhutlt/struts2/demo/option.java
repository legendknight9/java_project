/**
 * 
 */
package nhutlt.struts2.demo;

/**
 * @author nhutlt
 *
 */
public class option {
	private int answer;
	private String answerDescription;

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/**
	 * @return the answerDescription
	 */
	public String getAnswerDescription() {
		return answerDescription;
	}

	/**
	 * @param answerDescription the answerDescription to set
	 */
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}

	public option(int answer, String answerDescription) {
		this.setAnswer(answer);
		this.setAnswerDescription(answerDescription);
	}
}
