<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR PAGE</title>
</head>
<body>
    <br><br>
    <h3><c:out value="${error}"></c:out></h3>
    
    &nbsp&nbsp&nbsp&nbsp
	<a href="${link}">
		<input type="button" value="Main Menu"/>
    </a>
</body>
</html>