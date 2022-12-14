<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>EMPLOYEE FORM</title>
</head>
    <body>
    
       	<center><h3><c:out value="${title}"/></h3></center>
    	
    	<c:if test="${null != message}">
       		<center>
        		<h3><c:out value="${message}"/></h3>
        	</center>
    	</c:if>
    	
    	<form:form method="post" action ="${formAction}" modelAttribute="employee">
    	
    		<form:input name ="employeeId" path="employeeId" type="hidden" value="${employee.employeeId}"/>
    	    
    	    Enter Employee Name :<br>
        	<form:input name="employeeName" path="employeeName" type="text" value="${employee.employeeName}" placeholder="jon doe" pattern="((([A-Za-z]{2,}([ ]?)){1,}))(([.]?)([a-zA-Z]{1}))" required="required"/><br><br>

			Enter Employee Mail Id :<br>
    	    <form:input name="employeeMailId" path="employeeMailId" type="email" value="${employee.employeeMailId}" placeholder="ex:aaa@123gamil.com" pattern="((([A-Za-z0-9]{1,})([.]?)){1,})([a-z]{0,}?)([@]{1})(([a-z])*)((([.])([a-z]{2,3})){1,2})" required="required"/><br><br>

        	Enter Employee Contact Number :<br>
        	<form:input name="employeeContactNumber" path="employeeContactNumber" value="${employee.employeeContactNumber}" type="tel" placeholder="ex:9876543211" pattern="([6-9]{1}[0-9]{9})" required="required"/><br><br>

        	Enter Employee Salary :<br>
        	<form:input name="employeeSalary" path="employeeSalary" type="float" value="${employee.employeeSalary}" placeholder="ex:1000" pattern="([1-9]{1})([0-9]{1,8})((([.])([0-9]{1,2}))?)" required="required"/><br><br>

    	    Enter Employee Date Of birth :<br>
    	    <form:input name="employeeDateOfBirth" min="1960-08-12" max="2022-08-12" value="${employee.employeeDateOfBirth}" path="employeeDateOfBirth" type="date" placeholder="yyyy-mm-dd" required="required"/><br><br>
    	    
    	    <%--  <form:input name ="projects" path="projects" type="hidden" value="${employee.projects}"/> --%>

     	    <input type="submit" value="${submit}"></input>&nbsp&nbsp&nbsp
     	    
     	    <input type="reset" value="clear"></input><br><br>
     	    
            <a href="employeeManagement.jsp"><input type="button" value="Main Menu"></a>
             
 	    </form:form>
    </body>
</html>