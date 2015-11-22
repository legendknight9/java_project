<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page errorPage="errorpage.jsp"	 %>
<%@ page import="nhutlt.jsp.example.GetInformation"%>
<jsp:useBean id="getInfo" scope="application"
	class="nhutlt.jsp.example.GetInformation"></jsp:useBean>
<jsp:setProperty property="account" name="getInfo" />
<jsp:setProperty property="firstname" name="getInfo" />
<jsp:setProperty property="lastname" name="getInfo" />
<jsp:setProperty property="email" name="getInfo" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<form  method="post">
		<table>
			<tr>
				<td><b>Account</b></td>
				<td><input type="text" name="account"></td>
			</tr>
			<tr>
				<td><b>First name</b></td>
				<td><input type="text" name="firstname"></td>
			</tr>
			<tr>
				<td><b>Last name</b></td>
				<td><input type="text" name="lastname"></td>
			</tr>
			<tr>
				<td><b>Email</b></td>
				<td><input type="text" name="email"></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="Submit">
	</form>
	<%
		if (request.getParameter("account") != null && request.getParameter("firstname") != null 
			&& request.getParameter("lastname") != null && request.getParameter("email") != null
			&& request.getParameter("account") != "" && request.getParameter("firstname") != "" 
			&& request.getParameter("lastname") != "" && request.getParameter("email") != "") {
			response.sendRedirect("TraineeList.jsp");
		}
	 %>
</body>
</html>