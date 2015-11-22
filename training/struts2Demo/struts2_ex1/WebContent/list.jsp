<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>Welcome trainee</title>
</head>
<body>
<h2>Welcome <s:property value="firstName" /></h2>

<h3>Visited trainee</h3>
<table border="1">
	<tr>
	<th>First Name</th>
	<th>Last Name </th>
	<th>Email</th>
	</tr>
	<s:iterator value="list">
		<tr>
		<th><s:property value="firstName" /><br></th>
		<th><s:property value="lastName" /><br></th>
		<th><s:property value="email" /><br></th>
		</tr>
	</s:iterator>
</table>
</body>
</html>