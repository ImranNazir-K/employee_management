<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJECT EMPLOYEES</title>
</head>
<body>
    <br>
	<c:if test="${empty employees}">
    	<h3><c:out value="Employees Not Assigned"/></h3>
    </c:if>
    
	<c:if test="${!empty employees}">
		<h2><c:out value="List Of Employees"/></h2>
		<table border="1">
    		<thead>
		  		<tr>
		  		 	<td>ID</td>
          			<td>NAME</td>
          			<td>MAIL ID</td>
   	       			<td>SALARY</td>
          			<td>CONTACT NUMBER</td>
          			<td>DATE OF BIRTH</td>
  				</tr>
  			</thead>      
   		    <c:forEach var="employee" items="${employees}">
   				<tbody>
 	        		<tr>
 	        		    <td><c:out value="${employee.employeeId}"/></td>
      	    	    	<td><c:out value="${employee.employeeName}"/></td>
      					<td><c:out value="${employee.employeeMailId}"/></td>
      					<td><c:out value="${employee.employeeSalary}"/></td>
      					<td><c:out value="${employee.employeeContactNumber}"/></td>
    	  				<td><c:out value="${employee.employeeDateOfBirth}"/></td>
            		</tr>
		    	</tbody>
	    	</c:forEach>
      	</table>
	</c:if>
	<br>
	<a href="displayProjects.jsp">
		<input type="button" value="back"/>
	</a>
</body>
</html>