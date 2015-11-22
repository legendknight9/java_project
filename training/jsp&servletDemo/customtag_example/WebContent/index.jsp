<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="nhutlt" uri="/WEB-INF/mytags/mytags.tld"%>
<!-- 
<%@ page import="nhutlt.bean.formbean"%>
--> 
<jsp:useBean id="getInfo" scope="application"
	class="nhutlt.bean.formbean"></jsp:useBean>
<jsp:setProperty property="account" name="getInfo" />
<jsp:setProperty property="firstname" name="getInfo" />
<jsp:setProperty property="lastname" name="getInfo" />
<jsp:setProperty property="email" name="getInfo" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nhutlt:LoginForm method="post" 
		label1="Account" value1="account"
		label2="First Name" value2="firstname"
		label3="Last Name" value3="lastname"
		label4="Email" value4="email"  />
		
	<%! private HttpSession activeSession; %>
	<%	
		if (request.getParameter("account") != null && request.getParameter("firstname") != null 
			&& request.getParameter("lastname") != null && request.getParameter("email") != null
			&& request.getParameter("account") != "" && request.getParameter("firstname") != "" 
			&& request.getParameter("lastname") != "" && request.getParameter("email") != "") {
			activeSession = request.getSession(true);
			activeSession.putValue("account", getInfo.getAccount());
			activeSession.putValue("firstname", getInfo.getFirstname());
			activeSession.putValue("lastname", getInfo.getLastname());
			activeSession.putValue("email", getInfo.getEmail());
			activeSession.setMaxInactiveInterval(600);
			response.sendRedirect("success.jsp");
		}
	 %>
</body>
</html>