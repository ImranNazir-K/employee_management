<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<head>
    <title>PROJECT FORM</title>
    <body>
    
       	<center><h3><c:out value="${title}"/></h3></center>
    	
    	<c:if test="${null != message}">
       		<center>
        			<h3><c:out value="${message}"/></h3>
        		</center>
    	</c:if>
    	
    	<form:form action="${formAction}" method="post" modelAttribute="project">
    	
			<form:input type="hidden" path="" name="projectId" value="${project.projectId}"/><br>
		
    	    Enter Project Name :<br>
        	<form:input type="text" path="" name="projectName" value="${project.projectName}" pattern="((([a-zA-Z0-9]{3,})(([ ])([a-zA-Z0-9]){3,})?){1,})"/><br><br>

        	Enter Project Domain :<br>
        	<form:input type="text" path=""  name="projectDomain" value="${project.projectDomain}" pattern="((([A-Za-z]{1,}([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})"/><br><br>

	        Enter Project Description :<br>
    	    <form:input  name="projectDescription" path="" value="${project.projectDescription}"/><br><br>

        	<input type="reset" value="clear">
        	
        	<input type="submit" value="${submit}"><br><br>
        	
        	<a href="projectManagement.jsp"><input type="button" value="Main Menu"></a>
        </form:form>
    </body>
</head>