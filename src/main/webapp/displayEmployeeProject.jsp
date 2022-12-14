<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMPLOYEE PROJECTS</title>
</head>
<body>
	<br>
	
	<c:choose>
		<c:when test="${empty projects}">
			<h3><c:out value="Projects Not Assigned"></c:out></h3>
		</c:when>
	
		<c:otherwise>
	   	 	<h2><c:out value="List Of Projects"></c:out></h2>
	   	 	
	    	<table border="1">
	    		<thead>
   					<tr>
  						<td>ID</td>
   						<td>NAME</td>
   						<td>DESCRIPTION</td>
  						<td>DOMAIN</td>
  					</tr>
    			</thead>
   		 		
				<c:forEach var="project" items="${projects}">
   		    		<tbody>
			    		<tr>
   		 		    		<td><c:out value="${project.projectId}"/></td>
    		    			<td><c:out value="${project.projectName}"/></td>
        					<td><c:out value="${project.projectDescription}"/></td>
		    				<td><c:out value="${project.projectDomain}"/></td>
    		    		</tr>
    				</tbody>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br><br>
	<a href="displayEmployees.jsp">
		<input type="button" value="back"/>
	</a>
</body>
</html>