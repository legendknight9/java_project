/**
 * 
 */
package nhutlt.customtag;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author nhutlt
 *
 */
public class feedbacktag extends SimpleTagSupport {
	private String[] listattr;
	private String[] listlabel;
	private String listname;
	private final String ERRORLIST = "List is empty or null";
	private boolean nospecifiedlabel;
	private List list;
	
	/**
	 * @param nospecifiedlabel the nospecifiedlabel to set
	 */
	public void setNospecifiedlabel(String nospecifiedlabel) {
		this.nospecifiedlabel = Boolean.parseBoolean(nospecifiedlabel);
	}	

	/**
	 * @param listname the listname to set
	 */
	public void setListname(String listname) {
		this.listname = listname;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (list == null) {
//			list = (List) getJspContext().getAttribute(listname);
			out.println("<h3>" + ERRORLIST + "</h3>");
			return;
		}		
		
		int numberOfRow = list.size();
		if (numberOfRow <= 0) {
			out.println("<h3>" + ERRORLIST + "</h3>");
			return;
		}		
		
		if (nospecifiedlabel) {
			Method[] methods = list.get(0).getClass(). getMethods();
			List<String> attributeName = new ArrayList<String>();
			List<Method> getters = new ArrayList<Method>();
			for (Method met : methods) {
				String methodName = met.getName();
				if (methodName.startsWith("get")) {
					attributeName.add(methodName.substring(3));
					getters.add(met);
				}
			}
			// remove getClass method
			int getClassPosition = getters.size() - 1;
			getters.remove(getClassPosition);
			attributeName.remove(getClassPosition);
			
			out.println("<table border=\"1\">");
			
			// table header
			out.println("<tr>");
			for (String name : attributeName) {				
				out.println("<th>" + name + "</th>");				
			}			
			out.println("</tr>");	
			
			// table content
			for (int index = 0; index < numberOfRow; index++) {
				out.println("<tr>");
				for (Method met: getters) {
					try {
						
						out.println("<td>" + met.invoke(list.get(index), new Object[] {}).toString() + "</td>");
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
				out.println("</tr>");
			}
		} else {
			Method[] methods = list.get(0).getClass().getMethods();
			List<Method> getters = new ArrayList<Method>();
			
			for(String attr : listattr) {
				String FirstUpperCase = attr.toUpperCase().charAt(0) + attr.substring(1);
				for (Method met : methods) {
					String methodName = met.getName();
					if (methodName.equals("get" + FirstUpperCase)) {
						getters.add(met);
					}
				}
			}
			numberOfRow = list.size();
			out.println("<table border=\"1\">");
			
			// table header
			out.println("<tr>");
			for (String name : listlabel) {				
				out.println("<th>" + name + "</th>");				
			}			
			out.println("</tr>");
			
			// table content
			for (int index = 0; index < numberOfRow; index++) {
				out.println("<tr>");
				for (Method met: getters) {
					try {						
						out.println("<td>" + met.invoke(list.get(index), new Object[] {}).toString() + "</td>");
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
				out.println("</tr>");
			}
		}
	}

	/**
	 * @param listattr the listattr to set
	 */
	public void setListattr(String listattr) {
		this.listattr = listattr.split(",");
	}

	/**
	 * @param listlabel the listlabel to set
	 */
	public void setListlabel(String listlabel) {
		this.listlabel = listlabel.split(",");
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
}
