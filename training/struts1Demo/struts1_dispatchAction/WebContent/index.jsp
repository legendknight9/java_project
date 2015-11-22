<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html xhtml="true">

<head>
	<title></title>
</head>

<body>
<h3>Blank project</h3>
</body>
<html:form action="/login.do?method=mylogin">
Username : <html:text property="name" /> <br>
Password : <html:password property="pass" /><br>
<html:submit value="Login"></html:submit>
</html:form>

</html:html>