<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="deleteParticularProject.jsp" method="get">
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