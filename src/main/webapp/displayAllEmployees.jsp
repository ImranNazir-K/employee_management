<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DISPLAY</title>
</head>
<body>
	<h2><center>EMPLOYEES</center></h2>
	<table border="1">
    	<thead>
      		<tr>
          		<td>ID</td>
          		<td>NAME</td>
          		<td>MAIL ID</td>
          		<td>SALARY</td>
          		<td>CONTACT NUMBER</td>
          		<td>PROJECTS</td>
      		</tr>
   		</thead>      
   
   		<tbody>
    	
        	<c:forEach var="employee" items="${employees}">
            	<tr>
      	        	<td> <c:out value="${employee.employeeId}"/></td>
      	        	<td> <c:out value="${employee.employeeName}"/></td>
      				<td> <c:out value="${employee.employeeMailId}"/></td>
      				<td> <c:out value="${employee.employeeSalary}"/></td>
      				<td> <c:out value="${employee.employeeContactNumber}"/></td>
      		     	<td><a href="displayEmployeeProject?employeeId=${employee.employeeId}">projects</a></td>
            	</tr>
      		</c:forEach>
   		</tbody>
	</table>
	<br><br>
    
	<a href="displayEmployees.jsp">
        <input type="button" value="back"/>
    </a>&nbsp&nbsp
    
    <a href="employeeManagement.jsp">
        <input type="button" value="Main Menu"/>
    </a>
</body>
</html>