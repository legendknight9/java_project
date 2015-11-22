<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
</body>
</html>