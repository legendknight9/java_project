/**
 * 
 */
package nhutlt.demo.struts1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author nhutlt
 *
 */
public class login extends DispatchAction {
	public ActionForward mylogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		loginformbean myform = (loginformbean) form;
		request.setAttribute("input", myform);
		System.out.println("test");
		return mapping.findForward("success");
	}
}
