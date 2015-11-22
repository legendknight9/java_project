package nhutlt.jsp.customtag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TraineeListTag extends SimpleTagSupport{
//	private List<String> traineeList;
	
//	public void setTraineelist(List<String> list) {
//		this.traineeList = list;
//	}
	private String traineeList;
	
	public void setTraineelist(String list) {
	this.traineeList = list;
}
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("Trainee list : ");
//		for (String name : traineeList) {
			out.println(traineeList);
//		}
	}
	
}
