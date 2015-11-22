/**
 * 
 */
package nhutlt.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author nhutlt
 *
 */
public class logintag extends SimpleTagSupport {

	private String method;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String label1;
	private String label2;
	private String label3;
	private String label4;
	
	
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (method == null || method.length() == 0) {
			out.println("<%= new String(\"No method\") %>");
			return;
		} else {
			out.print("<form method=\"" + method + "\">");
		}
		out.print("<table>");
	
		if (label1 != null && value1 != null) {
			out.print("<tr>");
			out.print("<td><b>" + label1 + "</b></td>");
			out.print("<td><input type=\"text\" name=\"" + value1 + "\"></td>");		
			out.print("</tr>");
		}
		if (label2 != null && value2 != null) {
			out.print("<tr>");
			out.print("<td><b>" + label2 + "</b></td>");
			out.print("<td><input type=\"text\" name=\"" + value2 + "\"></td>");
			out.print("</tr>");
		}
		if (label3 != null && value3 != null) {
			out.print("<tr>");
			out.print("<td><b>" + label3 + "</b></td>");
			out.print("<td><input type=\"text\" name=\"" + value3 + "\"></td>");
			out.print("</tr>");
		}
		if (label4 != null && value4 != null) {
			out.print("<tr>");
			out.print("<td><b>" + label4 + "</b></td>");
			out.print("<td><input type=\"text\" name=\"" + value4 + "\"></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<input type=\"submit\" name=\"submit\" value=\"Submit\">");
		out.print("</form>");
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setvalue1(String value1) {
		this.value1 = value1;
	}

	public void setvalue2(String value2) {
		this.value2 = value2;
	}

	public void setvalue3(String value3) {
		this.value3 = value3;
	}

	public void setvalue4(String value4) {
		this.value4 = value4;
	}

	public void setlabel1(String label1) {
		this.label1 = label1;
	}

	public void setlabel2(String label2) {
		this.label2 = label2;
	}

	public void setlabel3(String label3) {
		this.label3 = label3;
	}

	public void setlabel4(String label4) {
		this.label4 = label4;
	}
	
}
