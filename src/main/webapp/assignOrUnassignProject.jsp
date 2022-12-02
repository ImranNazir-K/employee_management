<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE EMPLOYEE</title>
</head>
<body>
	<c:if test="${null != message}">
		<h3><c:out value="${message}"/></h3>
	</c:if>	
	
	<form action="${formAction}">
		<label for="employeeId"><br>Enter the Employee Id :</label><br>
		<input type="number" name="employeeId" ></input><br><br>
		<input type="submit" value="submit" ></input><br>
	</form>
</body>
</html>