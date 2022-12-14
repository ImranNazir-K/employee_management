<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
</head>
<body>
	<c:choose>
		<c:when test="${null == param.message}">
    	<h3>
        	<c:out value="THANK YOU"></c:out>
    	</h3>
    	</c:when>
    	
    	<c:otherwise>
    	<h3>
        	<c:out value="${param.message}"></c:out>
    	</h3>
			<a href="${param.link}">
			    <input type="button" value="back"></input>
			</a>
		</c:otherwise>
    </c:choose>	
</body>
</html>