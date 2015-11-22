<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<html>
<head>
<title><bean:message key="listpage.windowtitle" /></title>
</head>
<body>
<h2>
		<bean:message key="listpage.title" /> 
    	<bean:write name="submitForm" property="firstName" /> 
    	<bean:write name="submitForm" property="lastName" />  
</h2>	
		
	<bean:message key="listpage.listtitle" /> <BR/>
	<table border="1">
	<tr>
	<th><bean:message key="listpage.firstname" /> </th>
	<th><bean:message key="listpage.lastname" /> </th>
	<th><bean:message key="listpage.email" /></th>
	</tr>
  	<logic:iterate name="ListTrainee" id="var" >
		<tr>
		<td><bean:write name="var" property="firstName" /></td>
		<td><bean:write name="var" property="lastName" /></td>
		<td><bean:write name="var" property="email" /></td>
		</tr>
	</logic:iterate>
	</table>
		
	<html:form action="/feedback" method="GET">
	1. <bean:message key="listpage.feedbacktype1" /><BR/>
	<html:radio property="question1" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question1" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question1" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question1" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question1" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note1"></html:textarea><BR/>
	<BR/>
	2. <bean:message key="listpage.feedbacktype2" /><BR/>
	<html:radio property="question2" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question2" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question2" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question2" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question2" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note2"></html:textarea><BR/>
	<BR/>
	3. <bean:message key="listpage.feedbacktype3" /><BR/>
	<html:radio property="question3" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question3" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question3" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question3" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question3" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note3"></html:textarea><BR/>
	<BR/>
	4. <bean:message key="listpage.feedbacktype4" /><BR/>
	<html:radio property="question4" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question4" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question4" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question4" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question4" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note4"></html:textarea><BR/>
	<BR/>
	5. <bean:message key="listpage.feedbacktype5" /><BR/>
	<html:radio property="question5" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question5" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question5" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question5" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question5" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note5"></html:textarea><BR/>
	<BR/>
	6. <bean:message key="listpage.feedbacktype6" /><BR/>
	<html:radio property="question6" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question6" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question6" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question6" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question6" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note6"></html:textarea><BR/>
	<BR/>
	7. <bean:message key="listpage.feedbacktype7" /><BR/>
	<html:radio property="question7" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question7" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question7" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question7" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question7" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note7"></html:textarea><BR/>
	<BR/>
	8. <bean:message key="listpage.feedbacktype8" /><BR/>
	<html:radio property="question8" value="1"><bean:message key="listpage.feedbackrate1" /></html:radio><BR/>
	<html:radio property="question8" value="2"><bean:message key="listpage.feedbackrate2" /></html:radio><BR/>
	<html:radio property="question8" value="3"><bean:message key="listpage.feedbackrate3" /></html:radio><BR/>
	<html:radio property="question8" value="4"><bean:message key="listpage.feedbackrate4" /></html:radio><BR/>
	<html:radio property="question8" value="5"><bean:message key="listpage.feedbackrate5" /></html:radio><BR/>
	<html:textarea property="note8"></html:textarea><BR/>
	<BR/>
	<html:submit property="clickbutton">
	<bean:message key="listpage.button.Send" />
	</html:submit>
	</html:form>


</body>
</html>