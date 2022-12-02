<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${null != message}">
		<h3><c:out value="${message}"/></h3>
	</c:if>	
	<form action="displayParticularProject" method="post">
		<label for="projectId"><br>Enter the Project Id :</label>
		<input type="number" name="projectId" ></input><br><br>
		<input type="submit" value="submit"></input>
				
		&nbsp&nbsp&nbsp&nbsp
		<a href="deleteEmployees.jsp">
    		<input type="button" value="back"/>
    	</a>
	</form>
</body>
</html>