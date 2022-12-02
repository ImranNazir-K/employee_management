<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJECTS</title>
</head>
<body>
	<h2><center>PROJECTS</center></h2>
    <table border="1">
    	<thead>
    		<tr>
   				<td>ID</td>
   				<td>NAME</td>
   				<td>DESCRIPTION</td>
   				<td>DOMAIN</td>
   				<td>EMPLOYEES</td>
   			</tr>
    	</thead>
    	
        <tbody>
	    	<c:forEach var="project" items="${projects}">
	    	    <tr>
   	 		    	<td><c:out value="${project.projectId}"/></td>
    		    	<td><c:out value="${project.projectName}"/></td>
    	    		<td><c:out value="${project.projectDescription}"/></td>
    	    		<td><c:out value="${project.projectDomain}"/></td>
    	    		<td><a href="displayProjectEmployee?id=${project.projectId}">employees</a></td>
    	    	</tr>
        	</c:forEach>
    	</tbody>
    </table>
    
    <br><br>
	<a href="displayProjects.jsp">
        <input type="button" value="back"></input>
    </a>&nbsp&nbsp
    
	<a href="projectManagement.jsp">
        <input type="button" value="Main Menu"></input>
    </a>
</body>
</html>