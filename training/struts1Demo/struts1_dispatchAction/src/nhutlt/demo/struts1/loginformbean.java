/**
 * 
 */
package nhutlt.demo.struts1;

import org.apache.struts.action.ActionForm;

/**
 * @author nhutlt
 *
 */
public class loginformbean extends ActionForm {
	private String name, pass;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
