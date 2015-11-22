/**
 * 
 */
package nhutlt.struts1.demo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author nhutlt
 *
 */
public class submitForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719173696229552435L;
	
	private String firstName, lastName, email, clickbutton;

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
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if(firstName.trim().length() == 0) {
			errors.add("firstName", new ActionError("indexpage.error.firstnameempty"));
		}
		
		if(lastName.trim().length() == 0) {
			errors.add("lastName", new ActionError("indexpage.error.lastnameempty"));
		}
		
		if(email.trim().length() == 0) {
			errors.add("email", new ActionError("indexpage.error.emailempty"));
		}
		
		return errors;
	}	
}
