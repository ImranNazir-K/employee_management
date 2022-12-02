<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<head>
    <title>PROJECT FORM</title>
    <body>
    	<c:if test="${null != project}">
        		<center>
        			<h3><c:out value="UPDATE PROJECT"/></h3>
        		</center>
    	</c:if>
    	
    	<c:if test="${null == project}">
       		<center>
        			<h3><c:out value="CREATE PROJECT"/></h3>
        	</center>
    	</c:if>
    	
    	<form action="${formAction}" method="post">
    	
			<input type="hidden" name="projectId" value="${project.projectId}"><br>
		
    	    <label for="projectName">Enter Project Name :</label><br>
        	<input type="text" name="projectName" value="${project.projectName}" pattern="((([a-zA-Z0-9]{3,})(([ ])([a-zA-Z0-9]){3,})?){1,})"><br><br>

        	<label for="projectDomain">Enter Project Domain :</label><br>
        	<input type="text" name="projectDomain" value="${project.projectDomain}" pattern="((([A-Za-z]{1,}([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})"><br><br>

	        <label for="projectDescription">Enter Project Description :<br></label>
    	    <input  name="projectDescription" value="${project.projectDescription}"  ></input><br><br>

        	<input type="reset" value="clear">
        	<input type="submit" value="${submit}"><br>
        </form>
    </body>
</head>