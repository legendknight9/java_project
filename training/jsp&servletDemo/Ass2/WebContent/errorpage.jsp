<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page 
	isErrorPage="true"
	import="java.io.PrintWriter"
 %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Dectected Errors :
<br>
<b>
<% 
	PrintWriter writer = response.getWriter();
	writer.println(exception.toString());
%>
</b>
<br>
<form action="input.jsp" method="get">
<input type = "submit" value = "Back" >
</form>
</body>
</html>