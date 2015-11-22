<%@page import="org.apache.jasper.compiler.ErrorDispatcher"%>
<%@page import="javax.xml.ws.Action"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="nhutlt.jsp.example.GetInformation"
	import="java.sql.Connection" 
	import="java.sql.ResultSet"
	import="java.sql.SQLException" 
	import="java.sql.Statement"
	import="java.sql.DriverManager" 
	import="java.util.HashMap"
	import="java.util.Map"
	import="java.io.PrintWriter"
%>
<jsp:useBean id="getInfo" scope="application"
	class="nhutlt.jsp.example.GetInformation">
</jsp:useBean>
<%! 
	private HttpSession activeSession; 
	private Statement stat;
	private Connection con;
	private String query;
	private ResultSet rs;
	private Map<String, String> traineeList;
	private PrintWriter writer;
	
	private boolean save() {
		if (traineeList.containsValue(activeSession.getValue("account"))) {
			query = "Update guest set " 
					+ "firstname='"	+ activeSession.getValue("firstname") + "', " 
					+ "lastname='" + activeSession.getValue("lastname") + "', " 
					+ "email='" + activeSession.getValue("email") + " "
					+ "where idguest='" + traineeList.get(activeSession.getValue("account")) + "'";
		} else {
			query = "Insert into guest (account, firstname, lastname, email) value ('" 
					+ activeSession.getValue("account") + "', '" 
					+ activeSession.getValue("firstname") + "', '" 
					+ activeSession.getValue("lastname") + "', '" 
					+ activeSession.getValue("email") + "')";
		}
		try {
			stat.executeUpdate(query);
		} catch(SQLException e) {
			writer.print(e.toString());
			return false;
		}
		return true;
	};
	
%>
<%@ page errorPage="errorpage.jsp"	 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trainee List</title>
</head>
<body>

	<%
	writer = response.getWriter();
	String button = request.getParameter("button");
	if (button != null) {
		if (button.equals("Save")) {
			writer.println("<html><body><b>Saving...<br>");
			if(save()) {
				writer.println("Save successful!</b><br>" 
						+ "<form action=\"TraineeList.jsp\" method=\"get\">"
						+ "<input type=\"submit\" value=\"Back\" onclick=\"back()\">" 
						+ "</form></body></html>");	
				return;
			}
		} else {
			if (button != null) {
				response.sendRedirect("input.jsp");
			}
		}
	}
	activeSession = request.getSession(true);
	activeSession.putValue("account", getInfo.getAccount());
	activeSession.putValue("firstname", getInfo.getFirstname());
	activeSession.putValue("lastname", getInfo.getLastname());
	activeSession.putValue("email", getInfo.getEmail());
	activeSession.setMaxInactiveInterval(600);
	%>
	<table>
			<tr>
				<td><b>Account</b></td>
				<td><jsp:getProperty name="getInfo" property="account" /></td>
			</tr>
			<tr>
				<td><b>First name</b></td>
				<td><jsp:getProperty name="getInfo" property="firstname" /></td>
			</tr>
			<tr>
				<td><b>Last name</b></td>
				<td><jsp:getProperty name="getInfo" property="lastname" /></td>
			</tr>
			<tr>
				<td><b>Email</b></td>
				<td><jsp:getProperty name="getInfo" property="email" /></td>
			</tr>
	</table>
		<form method="post">
		<input type="submit" name="button" value="Save" /> 
		<input type="submit" name="button" value="Back" />
		</form>
		
			<% 
				traineeList = new HashMap<String, String>();
				stat = null;
				con = null;
				query = "Select * from guest ";
				rs = null;
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
					stat = con.createStatement();
					rs = stat.executeQuery(query);
				while(rs.next()) {
					traineeList.put(rs.getString("account"), rs.getString("idguest"));
				%>
					<%= rs.getString("lastname") + " " + rs.getString("firstname") %> <br>
		     <% } %>
	<!--  
	<form action = "input.jsp" method="get">
	<input type="submit" value="Back" />
	</form>
	-->

</body>
</html>