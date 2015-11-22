<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="nhutlt" uri="/WEB-INF/mytags/mytags.tld"%>
<%@page 
	import="nhutlt.bean.formbean"
	import="java.util.List"
	import="java.util.ArrayList"
	import="java.sql.Connection" 
	import="java.sql.ResultSet"
	import="java.sql.SQLException" 
	import="java.sql.Statement"
	import="java.sql.DriverManager" 
	import="java.util.List"
	import="java.util.ArrayList"
	import="nhutlt.DO.guestDO"
%>
<%! private HttpSession activeSession;%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	activeSession = request.getSession();
	List<formbean> listform = new ArrayList<formbean>();
	formbean form = new formbean();
	form.setAccount((String)activeSession.getValue("account"));
	form.setFirstname((String)activeSession.getValue("firstname"));
	form.setLastname((String)activeSession.getValue("lastname"));
	form.setEmail((String)activeSession.getValue("email"));
	listform.add(form);
	pageContext.setAttribute("mylist", listform);
	Integer index = 0;
%>
	<table>
			<tr>
				<td><b>Account</b></td>
				<td><%= form.getAccount() %></td>
			</tr>
			<tr>
				<td><b>First name</b></td>
				<td><%= form.getFirstname() %></td>
			</tr>
			<tr>
				<td><b>Last name</b></td>
				<td><%= form.getLastname() %></td>
			</tr>
			<tr>
				<td><b>Email</b></td>
				<td><%= form.getEmail() %></td>
			</tr>
	</table>
	
	
	<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
	Statement stat = con.createStatement();
	String query = "Select * from guest ";
	ResultSet rs = stat.executeQuery(query);
	List<guestDO> list = new ArrayList<guestDO>();
	while(rs.next()) {
		list.add(new guestDO(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email")));
	}
	pageContext.setAttribute("traineeList", list);
	%>
	<nhutlt:feedbackform nospecifiedlabel="false" 
						 list="${traineeList}" listattr="firstName,lastName,email"
						 listlabel="First Name,Last Name,Email Address"
						 />
		
</body>
</html>