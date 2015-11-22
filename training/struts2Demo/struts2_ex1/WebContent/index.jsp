<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>Welcome trainee</title>
<s:head/>
</head>
<body>


<s:form action="submitaction" > 
	<div align="center">
		<h2>Please Login</h2>
		<s:textfield label="Account" name="account"/>
		<s:textfield label="First Name" name="firstName"/>
		<s:textfield label="Last Name" name="lastName"/>
		<s:textfield label="Email" name="email"/>
		<s:submit value="Login" />
	</div>
</s:form>
</body>
</html>