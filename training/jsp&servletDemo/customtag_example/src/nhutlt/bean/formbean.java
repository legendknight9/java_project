/**
 * 
 */
package nhutlt.bean;

/**
 * @author nhutlt
 *
 */
public class formbean {
	private String account, firstname, lastname, email;

	public formbean() {
		account = null;
		firstname = null;
		lastname = null;
		email = null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
