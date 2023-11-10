<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Contractor list</title>
</head>
<body>

	
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of People</h2></caption>
        <tr>
        
            <th>ContractorID</th>            
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
           
            

        </tr>
         <c:forEach var="contractor" items="${listContractor}">
                <tr style="text-align:center">
                    <td><c:out value="${contractor.contractorID}" /></td>                   
                    <td><c:out value="${contractor.username}" /></td>
                    <td><c:out value="${contractor.password}" /></td>
                    <td><c:out value="${contractor.email}" /></td>
        </c:forEach>
    </table>
</div>

</body>
</html>