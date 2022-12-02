<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>EMPLOYEE FORM</title>
    <body>
    	<c:if test="${null != employee}">
        		<center>
        			<h3><c:out value="UPDATE EMPLOYEE"/></h3>
        		</center>
    	</c:if>
    	
    	<c:if test="${null == employee}">
       		<center>
        			<h3><c:out value="CREATE EMPLOYEE"/></h3>
        		</center>
    	</c:if>
    	
    	<form action ="${formAction}" method="post">
        	<input type="hidden" name="employeeId" value="${employee.employeeId}"><br>
    	
        	<label for="employeeName">Enter Employee Name :</label><br>
        	<input type="text" name="employeeName" value="${employee.employeeName}" pattern="((([A-Za-z]{2,}([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})"><br><br>

	        <label for="employeeName">Enter Employee Mail Id :</label><br>
    	    <input type="email" name="employeeMailId" value="${employee.employeeMailId}" pattern="((([A-Za-z0-9]{1,})([.]?)){1,})([a-z]{0,}?)([@]{1})(([a-z])*)((([.])([a-z]{2,3})){1,2})"><br><br>

        	<label for="employeeName">Enter Employee Contact Number :</label><br>
        	<input type="tel" name="employeeContactNumber" value="${employee.employeeContactNumber}" pattern="([6-9]{1}[0-9]{9})"><br><br>

        	<label for="employeeName">Enter Employee Salary :</label><br>
        	<input type="number" name="employeeSalary" value="${employee.employeeSalary}" pattern="([1-9]{1})([0-9]{1,8})((([.])([0-9]{1,2}))?)"><br><br>

    	    <label for="employeeName">Enter Employee Date Of birth :</label><br>
    	    <input type="date" name="employeeDateOfBirth" value="${employee.employeeDateOfBirth}"><br><br>

   		    <input type="reset" value="clear">
   		    
     	    <input type="submit" value="${submit}"><br><br>
     	    
            <a href="employeeManagement.jsp">
        		<input type="button" value="Main Menu"/>
    		</a>
        
 	    </form>
    </body>
</head>
</html>