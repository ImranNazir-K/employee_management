<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE</title>
</head>
<body>

	<c:if test="${null != message}">
		<h3><c:out value="${message}"/></h3>
	</c:if>	

	<c:choose>

		<c:when test = '${formAction.equals("unAssignProject") || formAction.equals("assignProject")}'>
		
			<c:if test='${formAction.equals("unAssignProject")}'>
				<center><h3><c:out value="Un-ASSIGN PROJECT"></c:out></h3></center><br>
			</c:if>
	
			<c:if test='${formAction.equals("assignProject")}'>
				<center><h3><c:out value="ASSIGN PROJECT"></c:out></h3></center><br>
			</c:if>
	
			<form action="${formAction}" method="post">
			
				<label for="employeeId"><br>Enter the Employee Id :</label><br>
				<input type="number" name="employeeId" ></input><br><br>
				
				<label for="projectId"><br>Enter the Project Id :</label><br>
				<input type="number" name="projectId" ></input><br><br>
				
				<input type="submit" value="submit" ></input>&nbsp&nbsp
		
				<a href="updateMenu.jsp">
    				<input type="button" value="back"/>
    			</a>
    			
    		</form>
    		
    	</c:when>
    	
   		<c:otherwise>
    
    		<c:if test='${formAction.equals("unAssignEmployee")}'>
				<center><h3><c:out value="Un-ASSIGN EMPLOYEE"></c:out></h3></center><br>
			</c:if>
	
			<c:if test='${formAction.equals("assignEmployee")}'>
				<center><h3><c:out value="ASSIGN EMPLOYEE"></c:out></h3></center><br>
			</c:if>
	
			<form action="${formAction}" method="post">
			
				<label for="projectId"><br>Enter the Project Id :</label><br>
				<input type="number" name="projectId" ></input><br><br>
				
				<label for="employeeId"><br>Enter the Employee Id :</label><br>
				<input type="number" name="employeeId" ></input><br><br>

				<input type="submit" value="submit" ></input>&nbsp&nbsp
			
				<a href="updateProjectMenu.jsp">
    				<input type="button" value="back"/>
    			</a>
    			
    		</form>
    		
		</c:otherwise>
        
	</c:choose>
	
</body>
</html>