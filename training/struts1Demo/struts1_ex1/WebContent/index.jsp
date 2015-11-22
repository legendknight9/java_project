<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html xhtml="true">

<head>
	<title><bean:message key="indexpage.windowtitle"/></title>
	<html:base/>
</head>

<body background="images/blueAndWhiteBackground.gif">
	<p/><p/><p/><p/><p/>
	<html:errors/>
    <div align="center">		
    
    	  	<html:form action="/submit">
    	  	<h2><bean:message key="listpage.title"/></h2><BR/>
    	  	
    	  	<bean:message key="indexpage.firstname" />
    	  	<html:text property="firstName"></html:text><BR/>
    	  	
    	  	<bean:message key="indexpage.lastname" />
    	  	<html:text property="lastName"></html:text><BR/>
    	  	
    	  	<bean:message key="indexpage.email" />
    	  	<html:text property="email"></html:text><BR/>
    	  	
  	    	<html:submit  property="clickbutton">
        	<bean:message key="indexpage.button.submit"/>
    		</html:submit>
	</html:form>
    </div>
</body>

</html:html>